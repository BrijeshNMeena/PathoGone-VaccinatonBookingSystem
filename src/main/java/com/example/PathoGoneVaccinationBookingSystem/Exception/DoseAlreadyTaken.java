package com.example.PathoGoneVaccinationBookingSystem.Exception;

public class DoseAlreadyTaken extends RuntimeException{

    public DoseAlreadyTaken(String message){
        super(message);
    }
}
