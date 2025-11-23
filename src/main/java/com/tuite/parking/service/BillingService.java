package com.tuite.parking.service;

import com.tuite.parking.domain.Vehicle;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class BillingService {

    public double calculateFee(Vehicle vehicle){
        long minutes = Duration.between(vehicle.getEntryTime(), LocalDateTime.now()).toMinutes();
        long hours = Math.max(1, (minutes +59)/60);

        return switch (vehicle.getType()){
            case MOTORCYCLE -> hours * 20.0;
            case CAR -> hours * 30.0;
            case BUS -> hours * 50.0;
        };
    }
}
