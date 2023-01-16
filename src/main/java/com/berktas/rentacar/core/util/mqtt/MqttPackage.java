package com.berktas.rentacar.core.util.mqtt;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttPackage {
    public String topic;
    public MqttMessage mqttMessage;

    public static MqttPackage from(String topic,MqttMessage mqttMessage){
        MqttPackage mqttPackage = new MqttPackage();
        mqttPackage.topic = topic;
        mqttPackage.mqttMessage = mqttMessage;
        return mqttPackage;
    }
}
