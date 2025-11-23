package com.tuite.parking.domain;

import com.tuite.parking.common.VehicleType;

import java.time.LocalDateTime;
import java.util.Objects;

public class Vehicle {
    private final String licensePlate;
    private final VehicleType type;
    private final LocalDateTime entryTime;

    public Vehicle(String licessePlate, VehicleType type){
        this.licensePlate = licessePlate;
        this.type = type;
        this.entryTime = LocalDateTime.now();
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public VehicleType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(licensePlate, vehicle.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(licensePlate);
    }
}
