package com.tuite.parking.domain;

import com.tuite.parking.common.VehicleType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ParkingLot {
    private final Map<Integer, ParkingLevel> levels = new TreeMap<>();
    private final Map<String, SlotLocation> vehicleIndex = new HashMap<>();

    public record SlotLocation(int levelId, int slotId){}

    public void addLevel(int levelId){
        levels.putIfAbsent(levelId, new ParkingLevel(levelId));
    }

    public void addSlots(int levelId, VehicleType type, int count){
        ParkingLevel level = levels.get(levelId);
        if(level == null) throw new IllegalArgumentException("Level not found");
        level.addSlots(type, count);
    }

    public Optional<SlotLocation> park(Vehicle vehicle){
        for(ParkingLevel level : levels.values()){
            Optional<ParkingSlot> slotOpt = level.findAvailableSlot(vehicle.getType());
            if(slotOpt.isPresent()){
                ParkingSlot slot = slotOpt.get();
                slot.park(vehicle);
                SlotLocation loc = new SlotLocation(level.getLevelId(), slot.getSlotId());
                vehicleIndex.put(vehicle.getLicensePlate(), loc);
                return Optional.of(loc);
            }
        }
        return Optional.empty();
    }
    public Optional<Vehicle> release(String licensePlate){
        SlotLocation loc = vehicleIndex.remove(licensePlate.toLowerCase());
        if(loc== null) return Optional.empty();
        ParkingLevel level = levels.get(loc.levelId());
        if(level == null) return Optional.empty();

        //find slot by id within the level
        Vehicle v = releaseFromLevel(level, loc.slotId());
        return Optional.ofNullable(v);
    }

    private Vehicle releaseFromLevel(ParkingLevel level, int slotId) {
        for(ParkingSlot s : level.getMotorcycleSlot()) if (s.getSlotId() == slotId) return s.release();
        for(ParkingSlot s : level.getCarSlots()) if (s.getSlotId() == slotId) return s.release();
        for(ParkingSlot s : level.getBusSlots()) if (s.getSlotId() == slotId) return s.release();
        return null;
    }


    public Map<Integer, ParkingLevel> getLevels() {
        return levels;
    }

    public Map<String, SlotLocation> getVehicleIndex() {
        return vehicleIndex;
    }
}
