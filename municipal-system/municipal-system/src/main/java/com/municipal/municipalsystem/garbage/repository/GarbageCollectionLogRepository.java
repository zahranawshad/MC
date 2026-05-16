package com.municipal.municipalsystem.garbage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.municipal.municipalsystem.garbage.GarbageCollectionLog;

public interface GarbageCollectionLogRepository extends JpaRepository<GarbageCollectionLog, Long> {
}