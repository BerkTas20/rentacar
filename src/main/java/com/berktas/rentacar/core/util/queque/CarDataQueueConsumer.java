package com.berktas.rentacar.core.util.queque;


import com.berktas.rentacar.business.abstracts.SaveGpsLogService;
import com.berktas.rentacar.core.util.mqtt.MqttPackage;
import com.berktas.rentacar.core.util.mqtt.MqttPushClient;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.model.entity.CarLog;
import com.berktas.rentacar.repository.CarLogRepository;
import com.berktas.rentacar.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


@RequiredArgsConstructor
public class CarDataQueueConsumer implements Runnable {

    private final CarDataQueue carDataQueue;
    private final CarRepository carRepository;
    private final CarLogRepository carLogRepository;
    private final SaveGpsLogService saveGpsLogService;
    private final MqttPushClient mqttPushClient;

    Logger logger = LoggerFactory.getLogger(CarDataQueueConsumer.class);



    @Override
    public void run() {
        logger.info(Thread.currentThread().getName() + " Started");
        while (true) {
            consume();
        }
    }

    public void consume() {
        try {

            MqttPackage data = carDataQueue.take();
            messageArrived(data.topic, data.mqttMessage);
        } catch (Exception e) {
            logger.warn("Exception while Consuming",e);
        }
    }


    public void messageArrived(String topic, MqttMessage mqttMessage) {
        try {
            if (MqttTopic.isMatched("/+/event/start", topic)) {
                logger.info("Start received, Data: " + mqttMessage.toString());
            }
            if (MqttTopic.isMatched("/+/event/gps", topic)) {
                saveGps(topic, mqttMessage);
            }

        } catch (Exception e) {
            logger.warn("Could not save CarLog", e);
        }
    }


    /**
     * @param topic topic
     * @param mqttMessage  arac takipten gelen veriler(body)
     */

    public void saveGps(String topic, MqttMessage mqttMessage) {
        //System.out.println("MQTT: " + topic + " " + new String(mqttMessage.getPayload()));
        //get imei from topic
        topic = topic.replaceAll("/", "");
        topic = topic.replaceAll("/event/gps", "");

        //vehicle in DB?
        Optional<Car> optionalVehicle = carRepository.findByImei(topic);
        if (!optionalVehicle.isPresent()) {
            logger.info("IMEI NOT IN DB, Received: " + mqttMessage.toString());
            return;
        }

        //parse mqtt
        LocationEvent locationEvent = LocationEvent.from(mqttMessage.getPayload());
        if (locationEvent == null) {
            logger.warn("No valid LocationEvent for Car IMEI " + optionalVehicle.get().getImei() + ", Received Data is: " + mqttMessage.toString());
            return;
        }

        Car car = optionalVehicle.get();

        //parse log
        CarLog log = CarLog.from(locationEvent, car);

        if (car.getLastLog() != null) {
            if (car.getLastLog().getMqttLogDateTime().isAfter(log.getMqttLogDateTime())) {
                logger.warn(String.format("Received Log is older than Last Log in DB(%s,%s)",car.getImei(),car.getLicencePlate()));
            }

            int newPno = log.getPackageNo();
            int oldPno = car.getLastLog().getPackageNo();
            int shouldPno = (oldPno + 1) % 256;

            if(newPno != shouldPno){
                logger.warn(String.format("Potential Package Loss for IMEI:%s, Vehicle:%s. Old: %d, New: %d, Should: %d",car.getImei(),car.getLicencePlate(),oldPno,newPno,shouldPno));
            }
        }

        if (!carLogRepository.existsByCar_IdAndMqttLogDateTime(car.getId(), log.getMqttLogDateTime())) {
            saveGpsLogService.saveGpsLog(log);
        }else{
            logger.info("Log already exists: " + mqttMessage.toString());
        }

    }


}

