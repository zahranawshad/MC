package com.municipal.municipalsystem.garbage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.municipal.municipalsystem.garbage.GarbageSchedule;

public interface GarbageScheduleRepository extends JpaRepository<GarbageSchedule, Long> {

    List<GarbageSchedule> findByArea(String area);

}