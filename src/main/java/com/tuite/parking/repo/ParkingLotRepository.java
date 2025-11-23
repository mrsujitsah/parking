package com.tuite.parking.repo;

import com.tuite.parking.domain.ParkingLot;
import org.springframework.stereotype.Repository;

@Repository
public class ParkingLotRepository {
    private final InMemoryStore store;

    public ParkingLotRepository(InMemoryStore store){
        this.store = store;
    }

    public ParkingLot get(){
        return store.getParkinglot();
    }
}
