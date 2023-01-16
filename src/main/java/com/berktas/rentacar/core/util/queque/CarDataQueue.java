package com.berktas.rentacar.core.util.queque;


import com.berktas.rentacar.core.util.mqtt.MqttPackage;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

@Component
public class CarDataQueue extends LinkedBlockingQueue<MqttPackage> {
}
