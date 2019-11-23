package com.diipgaid.config;

import com.diipgaid.constant.AppConstant;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class KafkaConfig {
    private static KafkaConfig instance;
    private Properties config;

    private KafkaConfig() {
        //get hostname
        String hostName = "";
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            System.out.println("ERROR: Cannot get host name");
        }
        config = new Properties();
        config.put("client.id", hostName);
        config.put("bootstrap.servers", AppConstant.BOOTSTRAP_SERVERS);
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("acks", AppConstant.ACKS);
        config.put("retries", AppConstant.RETRIES);
//        config.put("max.in.flight.requests.per.connection", AppConstant.RETRIES);
    }

    public static KafkaConfig getInstance() {
        if (instance == null) {
            instance = new KafkaConfig();
        }
        return instance;
    }

    public Properties getConfig() {
        return config;
    }

    public void setConfig(Properties config) {
        this.config = config;
    }
}
