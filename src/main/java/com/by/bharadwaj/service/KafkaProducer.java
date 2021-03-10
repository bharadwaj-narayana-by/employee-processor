package com.by.bharadwaj.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);


    @Value("${app.topic.dlq}")
    private String DLQ_TOPIC;


    @Value("${app.topic.employee-valid}")
    private String EMP_VALID_TOPIC;



    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;



    public void sendMessageDLQ(String message) {

        LOG.info("pushing message to DLQ: "+message);
        this.kafkaTemplate.send(DLQ_TOPIC, message);
    }


    public void sendMessageValidEmp(String message) {

        LOG.info("Valid message: "+message);
        this.kafkaTemplate.send(EMP_VALID_TOPIC, message);
    }
}
