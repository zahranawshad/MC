package com.municipal.municipalsystem.garbage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.municipal.municipalsystem.garbage.GarbageVehicle;

public interface GarbageVehicleRepository extends JpaRepository<GarbageVehicle, Long> {
}