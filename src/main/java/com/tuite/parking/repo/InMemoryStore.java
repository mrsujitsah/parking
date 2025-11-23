package com.tuite.parking.repo;

import com.tuite.parking.domain.ParkingLot;
import org.springframework.stereotype.Component;

@Component
public class InMemoryStore {
    private final ParkingLot parkinglot = new ParkingLot();

    public ParkingLot getParkinglot(){
        return parkinglot;
    }
}
