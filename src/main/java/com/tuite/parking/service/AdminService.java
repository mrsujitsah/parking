package com.tuite.parking.service;

import com.tuite.parking.common.VehicleType;
import com.tuite.parking.domain.ParkingLevel;
import com.tuite.parking.domain.ParkingLot;
import com.tuite.parking.domain.ParkingSlot;
import com.tuite.parking.dto.StatusResponse;
import com.tuite.parking.repo.ParkingLotRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminService {
    private final ParkingLotRepository lotRepo;

    public AdminService(ParkingLotRepository lotRepo) {
        this.lotRepo = lotRepo;
    }

    public void addLevel(int levelId){
        lotRepo.get().addLevel(levelId);
    }

    public void addSlots(int levelId, VehicleType type, int count){
        lotRepo.get().addSlots(levelId, type, count);
    }

    public StatusResponse status(){
        ParkingLot lot = lotRepo.get();
        Map<Integer, StatusResponse.LevelStatus> result = new HashMap<>();

        for(Map.Entry<Integer, ParkingLevel> e : lot.getLevels().entrySet()){
            ParkingLevel level = e.getValue();
            int total = level.totalSlots();
            int occupied =0;
            occupied += (int) level.getMotorcycleSlot().stream().filter(ParkingSlot::isOccupied).count();
            occupied += (int) level.getCarSlots().stream().filter(ParkingSlot::isOccupied).count();
            occupied += (int) level.getBusSlots().stream().filter(ParkingSlot::isOccupied).count();
            result.put(e.getKey(), new StatusResponse.LevelStatus(total, occupied, total-occupied));
        }
        return new StatusResponse(result);
    }
}
