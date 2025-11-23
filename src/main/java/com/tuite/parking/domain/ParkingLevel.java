package com.tuite.parking.domain;

import com.tuite.parking.common.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingLevel {
    private final int levelId;
    private final List<ParkingSlot> motorcycleSlot = new ArrayList<>();
    private final List<ParkingSlot> carSlots = new ArrayList<>();
    private final List<ParkingSlot> busSlots = new ArrayList<>();

    public ParkingLevel(int levelId) {
        this.levelId = levelId;
    }

    public void addSlots(VehicleType type, int count){
        List<ParkingSlot> target = switch(type){
            case MOTORCYCLE -> motorcycleSlot;
            case CAR ->  carSlots;
            case BUS ->  busSlots;
        };
        int startId = totalSlots() +1;
        for(int i=0; i<count; i++){
            target.add(new ParkingSlot(startId +i, type));
        }
    }

    public Optional<ParkingSlot> findAvailableSlot(VehicleType type){
        List<ParkingSlot> target = switch (type){
            case MOTORCYCLE -> motorcycleSlot;
            case CAR -> carSlots;
            case BUS -> busSlots;
        };
        return target.stream().filter(s -> !s.isOccupied()).findFirst();
    }

    public int totalSlots(){
        return motorcycleSlot.size() + carSlots.size() + busSlots.size();
    }

    public int getLevelId() {
        return levelId;
    }

    public List<ParkingSlot> getMotorcycleSlot() {
        return motorcycleSlot;
    }

    public List<ParkingSlot> getCarSlots() {
        return carSlots;
    }

    public List<ParkingSlot> getBusSlots() {
        return busSlots;
    }
}
