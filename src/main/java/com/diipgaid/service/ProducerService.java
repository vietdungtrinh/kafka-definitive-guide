package com.diipgaid.service;

import com.diipgaid.config.KafkaConfig;
import com.diipgaid.constant.AppConstant;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

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
//            System.out.println("Sending SYNC record to broker");
//            producer.send(record);
//            System.out.println("Sent record SYNC to broker successfully");

            System.out.println("Sending ASYNC record to broker");
            producer.send(record, new ProducerCallback());
            System.out.println("Sent record ASYNC to broker successfully");

            Thread thWait = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thWait.setName("Producer Wait");
            thWait.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class ProducerCallback implements Callback {
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e != null) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } else {
                System.out.println(String.format("Received ACK: topic=%s partition=%d offset=%d", recordMetadata.topic(),
                        recordMetadata.partition(), recordMetadata.offset()));
            }
        }
    }
}
