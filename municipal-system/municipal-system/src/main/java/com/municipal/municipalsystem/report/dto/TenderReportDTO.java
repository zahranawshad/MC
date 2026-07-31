package com.municipal.municipalsystem.report.dto;

import java.time.LocalDate;

public class TenderReportDTO {

    private Long tenderId;
    private String title;
    private Double estimatedBudget;
    private LocalDate closingDate;
    private String status;
    private int totalBids;

    public TenderReportDTO() {
    }

    public Long getTenderId() {
        return tenderId;
    }

    public void setTenderId(Long tenderId) {
        this.tenderId = tenderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getEstimatedBudget() {
        return estimatedBudget;
    }

    public void setEstimatedBudget(Double estimatedBudget) {
        this.estimatedBudget = estimatedBudget;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalBids() {
        return totalBids;
    }

    public void setTotalBids(int totalBids) {
        this.totalBids = totalBids;
    }
}
