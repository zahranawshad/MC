package com.municipal.municipalsystem.tax;

import com.municipal.municipalsystem.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxRepository extends JpaRepository<Tax, Long> {

    List<Tax> findByUser(User user);

    List<Tax> findByStatusIn(List<TaxStatus> statuses);

    long countByStatus(TaxStatus status);

    List<Tax> findByStatus(TaxStatus status);

    List<Tax> findByUserArea(String area);

    List<Tax> findByTaxType(TaxType taxType);

}