package com.municipal.municipalsystem.tender.repository;

import com.municipal.municipalsystem.tender.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenderRepository
        extends JpaRepository<Tender, Long> {

    long countByStatus(String status);

}