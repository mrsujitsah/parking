package com.tuite.parking.web;

import com.tuite.parking.dto.ExitResponse;
import com.tuite.parking.dto.ParkRequest;
import com.tuite.parking.dto.ParkResponse;
import com.tuite.parking.error.BadRequestException;
import com.tuite.parking.error.NotFoundException;
import com.tuite.parking.service.ParkingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {
    private final ParkingService service;

    public ParkingController(ParkingService service) {
        this.service = service;
    }

    @PostMapping("/park")
    public ParkResponse park(@Valid @RequestBody ParkRequest request) throws BadRequestException {
        return service.park(request.getType(), request.getLicensePlate());
    }

    @PostMapping("/exit/{plate}")
    public ExitResponse exit(@PathVariable String plate) throws NotFoundException {
        double fee = service.exit(plate);
        return new ExitResponse(true, "Exited", fee);
    }
}
