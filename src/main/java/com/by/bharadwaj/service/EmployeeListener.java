package com.by.bharadwaj.service;

import com.by.bharadwaj.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class EmployeeListener {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeListener.class);

    @Autowired
    private KafkaProducer kafkaProducer;



    @KafkaListener(topics = "${app.topic.employee}",groupId = "json",containerFactory = "kafkaListenerContainerFactory")
    public void receive( String data) {
        LOG.info("received data='{}'", data);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Employee emp = mapper.readValue(data,Employee.class);
            LOG.info("working:data="+emp);
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Employee>> violations = validator.validate(emp);

            if(!violations.isEmpty()) {

                for (ConstraintViolation<Employee> violation : violations) {
                    LOG.error(violation.getMessage());
                }
                throw new Exception("There were validation errors");
            }else {
                kafkaProducer.sendMessageValidEmp(data);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
            kafkaProducer.sendMessageDLQ(data);
        }
    }
}
