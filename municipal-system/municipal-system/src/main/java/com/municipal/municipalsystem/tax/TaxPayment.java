package com.municipal.municipalsystem.tax;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class TaxPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "tax_id")
    private Tax tax;

    private LocalDateTime paymentDate;

    private String paymentMethod; // CASH / CHEQUE

    private String receiptNumber;

    public TaxPayment() {
        this.paymentDate = LocalDateTime.now();
        this.receiptNumber = generateReceiptNumber();
    }

    private String generateReceiptNumber() {
        return "REC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }
}