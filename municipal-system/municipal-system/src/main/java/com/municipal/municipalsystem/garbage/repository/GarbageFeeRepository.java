package com.municipal.municipalsystem.garbage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.municipal.municipalsystem.garbage.GarbageFee;
import java.util.List;

public interface GarbageFeeRepository
        extends JpaRepository<GarbageFee, Long> {

    List<GarbageFee> findByCitizenId(Long citizenId);

    long countByStatus(String status);

    List<GarbageFee> findByStatus(String status);

}