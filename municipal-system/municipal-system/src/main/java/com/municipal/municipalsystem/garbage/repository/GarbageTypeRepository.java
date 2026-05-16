package com.municipal.municipalsystem.garbage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.municipal.municipalsystem.garbage.GarbageType;

public interface GarbageTypeRepository extends JpaRepository<GarbageType, Long> {
}