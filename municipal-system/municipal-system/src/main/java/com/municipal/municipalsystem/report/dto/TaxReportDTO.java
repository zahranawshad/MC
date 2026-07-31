package com.municipal.municipalsystem.report.dto;

import java.time.LocalDate;

public class TaxReportDTO {

    private Long taxId;
    private String citizenName;
    private String taxType;
    private Double amount;
    private Double penalty;
    private Double totalAmount;
    private LocalDate dueDate;
    private String status;

    public TaxReportDTO() {
    }

    public TaxReportDTO(Long taxId,
                        String citizenName,
                        String taxType,
                        Double amount,
                        Double penalty,
                        Double totalAmount,
                        LocalDate dueDate,
                        String status) {

        this.taxId = taxId;
        this.citizenName = citizenName;
        this.taxType = taxType;
        this.amount = amount;
        this.penalty = penalty;
        this.totalAmount = totalAmount;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public String getCitizenName() {
        return citizenName;
    }

    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPenalty() {
        return penalty;
    }

    public void setPenalty(Double penalty) {
        this.penalty = penalty;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}