package com.tuite.parking.repo;

import org.springframework.stereotype.Repository;

@Repository
public class ParkingLotRepository {
    private final InMemoryStore store;

    public ParkingLotRepository(InMemoryStore store){
        this.store = store;
    }

    public InMemoryStore getStore() {
        return store;
    }
}
