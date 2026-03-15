package com.pm.analytics_service.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;

@Service
public class KafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger("KafkaConsumer.class");
    @KafkaListener(topics="patient", groupId = "analytics-service")
    public void consumeEvent(ConsumerRecord<String, byte[]> record) {
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(record.value());
            log.info("Info received in consumer {} {} {} ", patientEvent.getName(), patientEvent.getEmail(), patientEvent.getPatienId().toString());
        } catch(InvalidProtocolBufferException ex) {
            log.error("Error desrializing {} ", ex.getMessage());
        }
        

    }
}
