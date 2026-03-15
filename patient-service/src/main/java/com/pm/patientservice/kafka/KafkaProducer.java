package com.pm.patientservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import patient.events.PatientEvent;
import com.pm.patientservice.model.Patient;

@Service
public class KafkaProducer {
    private Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Patient patient) {
        PatientEvent event = PatientEvent.newBuilder()
                        .setPatienId(patient.getId().toString())
                        .setName(patient.getName())
                        .setEmail(patient.getEmail())
                        .setEventType("PATIENT_CREATED")
                        .build(); 
        try {
            kafkaTemplate.send("patient", event.toByteArray());

        } catch(Exception ex) {
            log.error("Error sending PatientCreated event: {}", event);
        }

    }

}
