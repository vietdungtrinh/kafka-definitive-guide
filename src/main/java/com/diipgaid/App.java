package com.diipgaid;

import com.diipgaid.constant.AppConstant;
import com.diipgaid.service.ProducerService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        App app = new App();
        app.run();
    }

    private void run() {
        // init producer
        ProducerService producerService = new ProducerService();
        String topic = AppConstant.TOPIC;
        String key = "destination";
        String value = "Vietnam";
        producerService.send(topic, key, value);
    }
}
