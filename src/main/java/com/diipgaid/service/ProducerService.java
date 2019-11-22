package com.diipgaid.service;

import com.diipgaid.config.KafkaConfig;
import com.diipgaid.constant.AppConstant;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerService {
    private KafkaProducer<String, String> producer;

    public ProducerService() {
        producer = new KafkaProducer(KafkaConfig.getInstance().getConfig());
    }

    public KafkaProducer<String, String> getProducer() {
        return producer;
    }

    public void setProducer(KafkaProducer<String, String> producer) {
        this.producer = producer;
    }

    /**
     * https://docs.confluent.io/current/clients/java.html#initialization
     */
    public void send(String topic, String key, String value) {
        // init ProducerRecord
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

        // send to broker
        try {
            System.out.println("Sending record to broker");
            producer.send(record);
            System.out.println("Sent record to broker successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
