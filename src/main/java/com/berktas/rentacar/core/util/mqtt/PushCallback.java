package com.berktas.rentacar.core.util.mqtt;

import com.berktas.rentacar.core.util.queque.CarDataQueue;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PushCallback implements MqttCallback {

    @Autowired
    MqttPushClient mqttPushClient;

    @Autowired
    CarDataQueue carDataQueue;

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("connectionLost");
        throwable.printStackTrace();
        mqttPushClient.connect();
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) {
        log.info(s);
        log.info(mqttMessage.toString());
        mqttMessage.getPayload();
        try {
            MqttPackage mqttPackage = MqttPackage.from(s,mqttMessage);
            carDataQueue.put(mqttPackage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("deliveryComplete");
    }
}
