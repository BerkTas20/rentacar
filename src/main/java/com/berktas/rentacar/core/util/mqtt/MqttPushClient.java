package com.berktas.rentacar.core.util.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 * @author bam
 * 2020 March 5th 2013
 * MqttPushClient.java
 *
 */
@Component
public class MqttPushClient {
    private static final Logger logger = LoggerFactory.getLogger(MqttPushClient.class);
    private MqttClient client;

    private final String clientID = "MASTER";
    private final String hostName = "tcp://vm0.aselsis.com";
    private final String port = "1883";
    private final String userName = "";
    private final String password = "";

//    @Autowired
//    private PushCallback pushCallback;  //burada bi sıkıntı var . Beanler çakışıyor

    public MqttPushClient() {

    }

    @PostConstruct
    public void init() {
        System.out.println("MqttPushClient");


        ///@TODO uncomment when uploading
        connect();
        subscribe("#", 0);
    }

    public void connect() {
        try {

            client = new MqttClient(hostName, MqttAsyncClient.generateClientId(), new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setAutomaticReconnect(true);

            options.setConnectionTimeout(30);
            options.setKeepAliveInterval(60);
            try {
//                client.setCallback(pushCallback);
                client.connect(options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publish(int qos, boolean retained, String topic, String pushMessage) {
        MqttMessage message = new MqttMessage();
        message.setQos(qos);
        message.setRetained(retained);
        message.setPayload(pushMessage.getBytes());
        MqttTopic mTopic = client.getTopic(topic);
        if (null == mTopic) {
            logger.error("topic not exist");
        }
        MqttDeliveryToken token;
        try {
            token = mTopic.publish(message);
            token.waitForCompletion();
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * Subscribe to a topic
     *
     * @param topic theme
     * @param qos   Connection mode
     */
    public void subscribe(String topic, int qos) {
        logger.info("Start subscribing to topics" + topic);
        try {
            client.subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}