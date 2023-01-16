package com.berktas.rentacar.core.util.queque;


import com.berktas.rentacar.business.abstracts.CarLogService;
import com.berktas.rentacar.business.abstracts.SaveGpsLogService;
import com.berktas.rentacar.core.util.mqtt.MqttPushClient;
import com.berktas.rentacar.repository.CarLogRepository;
import com.berktas.rentacar.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CarDataQueueConfig {

    Logger logger = LoggerFactory.getLogger(CarDataQueueConfig.class);


    private final CarDataQueue mqttMessageQueue;

    private final CarRepository carRepository;

    private final CarLogRepository carLogRepository;

    private final SaveGpsLogService saveGpsLogService;

    private final CarLogService carLogService;

    private final MqttPushClient mqttPushClient;

    @Bean
    public void startMqttConsumerThread() {

        for (int i = 0; i < 1; i++) {
            Thread thread = new Thread(new CarDataQueueConsumer(
                    mqttMessageQueue,
                    carRepository,
                    carLogRepository,
                    saveGpsLogService, mqttPushClient));
            thread.start();
            thread.setName("MqttMessageQueueConsumerThread[" + i + "]");
            logger.info("Starting " + thread.getName());
            //System.out.println(thread.getName());
        }
    }


}
