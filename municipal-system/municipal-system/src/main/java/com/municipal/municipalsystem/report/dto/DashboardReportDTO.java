package com.municipal.municipalsystem.report.dto;

public class DashboardReportDTO {

    // Users
    private long totalUsers;
    private long totalCitizens;
    private long totalBusinesses;
    private long totalAdmins;

    // Taxes
    private long totalTaxes;
    private long paidTaxes;
    private long pendingTaxes;
    private long overdueTaxes;
    private double totalTaxRevenue;

    // Garbage
    private long totalGarbageFees;
    private long paidGarbageFees;
    private long pendingGarbageFees;
    private double totalGarbageRevenue;

    // Tenders
    private long totalTenders;
    private long openTenders;
    private long awardedTenders;
    private long closedTenders;
    private long totalBids;

    public DashboardReportDTO() {
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalCitizens() {
        return totalCitizens;
    }

    public void setTotalCitizens(long totalCitizens) {
        this.totalCitizens = totalCitizens;
    }

    public long getTotalBusinesses() {
        return totalBusinesses;
    }

    public void setTotalBusinesses(long totalBusinesses) {
        this.totalBusinesses = totalBusinesses;
    }

    public long getTotalAdmins() {
        return totalAdmins;
    }

    public void setTotalAdmins(long totalAdmins) {
        this.totalAdmins = totalAdmins;
    }

    public long getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(long totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public long getPaidTaxes() {
        return paidTaxes;
    }

    public void setPaidTaxes(long paidTaxes) {
        this.paidTaxes = paidTaxes;
    }

    public long getPendingTaxes() {
        return pendingTaxes;
    }

    public void setPendingTaxes(long pendingTaxes) {
        this.pendingTaxes = pendingTaxes;
    }

    public long getOverdueTaxes() {
        return overdueTaxes;
    }

    public void setOverdueTaxes(long overdueTaxes) {
        this.overdueTaxes = overdueTaxes;
    }

    public double getTotalTaxRevenue() {
        return totalTaxRevenue;
    }

    public void setTotalTaxRevenue(double totalTaxRevenue) {
        this.totalTaxRevenue = totalTaxRevenue;
    }

    public long getTotalGarbageFees() {
        return totalGarbageFees;
    }

    public void setTotalGarbageFees(long totalGarbageFees) {
        this.totalGarbageFees = totalGarbageFees;
    }

    public long getPaidGarbageFees() {
        return paidGarbageFees;
    }

    public void setPaidGarbageFees(long paidGarbageFees) {
        this.paidGarbageFees = paidGarbageFees;
    }

    public long getPendingGarbageFees() {
        return pendingGarbageFees;
    }

    public void setPendingGarbageFees(long pendingGarbageFees) {
        this.pendingGarbageFees = pendingGarbageFees;
    }

    public double getTotalGarbageRevenue() {
        return totalGarbageRevenue;
    }

    public void setTotalGarbageRevenue(double totalGarbageRevenue) {
        this.totalGarbageRevenue = totalGarbageRevenue;
    }

    public long getTotalTenders() {
        return totalTenders;
    }

    public void setTotalTenders(long totalTenders) {
        this.totalTenders = totalTenders;
    }

    public long getOpenTenders() {
        return openTenders;
    }

    public void setOpenTenders(long openTenders) {
        this.openTenders = openTenders;
    }

    public long getAwardedTenders() {
        return awardedTenders;
    }

    public void setAwardedTenders(long awardedTenders) {
        this.awardedTenders = awardedTenders;
    }

    public long getClosedTenders() {
        return closedTenders;
    }

    public void setClosedTenders(long closedTenders) {
        this.closedTenders = closedTenders;
    }

    public long getTotalBids() {
        return totalBids;
    }

    public void setTotalBids(long totalBids) {
        this.totalBids = totalBids;
    }
}