package com.tuite.parking.models;

import com.tuite.parking.utils.VehicleType;
import jakarta.persistence.*;

@Entity
@Table
public class ParkingSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slotId;
    private VehicleType vehicleType;
    private boolean isOccupied;

    public ParkingSlot(Long slotId, VehicleType vehicleType){
        this.slotId = slotId;
        this.vehicleType = vehicleType;
        this.isOccupied = false;
    }


    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
