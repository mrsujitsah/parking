package com.tuite.parking.service;

import com.tuite.parking.common.VehicleType;
import com.tuite.parking.domain.ParkingLot;
import com.tuite.parking.domain.Vehicle;
import com.tuite.parking.dto.ParkResponse;
import com.tuite.parking.error.BadRequestException;
import com.tuite.parking.error.NotFoundException;
import com.tuite.parking.repo.ParkingLotRepository;
import com.tuite.parking.repo.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingService {
    private final ParkingLotRepository lotRepo;
    private final VehicleRepository vehicleRepository;
    private final BillingService billingService;

    public ParkingService(ParkingLotRepository lotRepo, VehicleRepository vehicleRepository, BillingService billingService) {
        this.lotRepo = lotRepo;
        this.vehicleRepository = vehicleRepository;
        this.billingService = billingService;
    }

    public ParkResponse park(VehicleType type, String plate) throws BadRequestException {
        if(vehicleRepository.find(plate) != null){
            throw new BadRequestException("Vehicle already parked: "+ plate);
        }
        Vehicle vehicle = new Vehicle(plate, type);
        ParkingLot lot = lotRepo.get();

        var locOpt = lot.park(vehicle);
        if(locOpt.isEmpty()){
            return new ParkResponse(false, "No slot available", null, null);
        }
        vehicleRepository.save(vehicle);
        var loc = locOpt.get();
        return new ParkResponse(true, "Parked", loc.levelId(), loc.slotId());
    }

    public double exit(String plate) throws NotFoundException {
        Vehicle vehicle = vehicleRepository.remove(plate);
        if(vehicle == null){
            throw new NotFoundException("Vehicle not present in lot: "+ plate);
        }

        return billingService.calculateFee(vehicle);
    }
}
