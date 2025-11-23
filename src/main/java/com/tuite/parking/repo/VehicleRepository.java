package com.tuite.parking.repo;

import com.tuite.parking.domain.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class VehicleRepository {
    private final Map<String, Vehicle> byPlate = new ConcurrentHashMap<>();

    public void save(Vehicle v){
        byPlate.put(v.getLicensePlate(), v);
    }

    public Vehicle find(String licensePlate){
        return byPlate.get(licensePlate.toUpperCase());
    }

    public Vehicle remove(String licensePlate){
        return byPlate.remove(licensePlate.toUpperCase());
    }
}
