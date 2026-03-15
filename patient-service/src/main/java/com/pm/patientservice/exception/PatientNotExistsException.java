package com.pm.patientservice.exception;

public class PatientNotExistsException extends RuntimeException {
    public PatientNotExistsException(String message){ super(message);}
}
