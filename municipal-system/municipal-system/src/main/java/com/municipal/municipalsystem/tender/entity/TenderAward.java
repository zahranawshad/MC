package com.municipal.municipalsystem.tender.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tender_awards")
public class TenderAward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate awardDate;

    private String remarks;

    @OneToOne
    private Tender tender;

    @OneToOne
    private Bid bid;

    public TenderAward() {}

    public Long getId() { return id; }

    public LocalDate getAwardDate() { return awardDate; }
    public void setAwardDate(LocalDate awardDate) { this.awardDate = awardDate; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public Tender getTender() { return tender; }
    public void setTender(Tender tender) { this.tender = tender; }

    public Bid getBid() { return bid; }
    public void setBid(Bid bid) { this.bid = bid; }



}