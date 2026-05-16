package com.municipal.municipalsystem.tender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.municipal.municipalsystem.tender.entity.TenderAward;

public interface TenderAwardRepository extends JpaRepository<TenderAward, Long> {
}