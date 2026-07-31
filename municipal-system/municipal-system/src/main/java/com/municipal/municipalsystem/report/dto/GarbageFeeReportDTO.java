package com.municipal.municipalsystem.report.dto;

import java.time.LocalDate;

public class GarbageFeeReportDTO {

    private Long feeId;
    private String citizenName;
    private String area;
    private double weeklyKg;
    private double yearlyKg;
    private double ratePerKg;
    private double amount;
    private LocalDate dueDate;
    private String status;

    public GarbageFeeReportDTO() {
    }

    public Long getFeeId() {
        return feeId;
    }

    public void setFeeId(Long feeId) {
        this.feeId = feeId;
    }

    public String getCitizenName() {
        return citizenName;
    }

    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getWeeklyKg() {
        return weeklyKg;
    }

    public void setWeeklyKg(double weeklyKg) {
        this.weeklyKg = weeklyKg;
    }

    public double getYearlyKg() {
        return yearlyKg;
    }

    public void setYearlyKg(double yearlyKg) {
        this.yearlyKg = yearlyKg;
    }

    public double getRatePerKg() {
        return ratePerKg;
    }

    public void setRatePerKg(double ratePerKg) {
        this.ratePerKg = ratePerKg;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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