package com.tuite.parking.domain;

import com.tuite.parking.common.VehicleType;

public class ParkingSlot {
    private final int slotId;
    private final VehicleType allowedType;
    private Vehicle currentVehicle;

    public ParkingSlot(int slotId, VehicleType allowedType) {
        this.slotId = slotId;
        this.allowedType = allowedType;
    }

    public boolean isOccupied(){
        return currentVehicle != null;
    }

    public boolean canPark(Vehicle vehicle){
        return !isOccupied() && (allowedType == vehicle.getType() || allowedType == VehicleType.BUS && vehicle.getType() == VehicleType.BUS);
    }

    public void park(Vehicle vehicle){
        this.currentVehicle = vehicle;
    }

    public Vehicle release(){
        Vehicle v = this.currentVehicle;
        this.currentVehicle = null;
        return v;
    }

    public int getSlotId() {
        return slotId;
    }

    public VehicleType getAllowedType() {
        return allowedType;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }
}
