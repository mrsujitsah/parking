package com.tuite.parking.repository;

import com.tuite.parking.models.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingSlot, Long> {
}
