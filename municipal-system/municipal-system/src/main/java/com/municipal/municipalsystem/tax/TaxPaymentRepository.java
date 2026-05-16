package com.municipal.municipalsystem.tax;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxPaymentRepository extends JpaRepository<TaxPayment, Long> {
}