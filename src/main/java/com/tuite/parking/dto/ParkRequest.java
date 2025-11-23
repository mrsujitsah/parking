package com.tuite.parking.dto;

import com.tuite.parking.common.VehicleType;
import jakarta.validation.constraints.NotNull;


public class ParkRequest {
    @NotNull
    private VehicleType type;
    @NotNull
    private String licensePlate;

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
