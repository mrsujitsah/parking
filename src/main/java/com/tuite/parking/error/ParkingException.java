package com.tuite.parking.error;

public class ParkingException extends RuntimeException{
    public ParkingException(String msg){
        super(msg);
    }
}
