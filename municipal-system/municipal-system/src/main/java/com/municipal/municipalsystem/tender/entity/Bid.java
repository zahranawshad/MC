package com.municipal.municipalsystem.tender.entity;

import com.municipal.municipalsystem.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double bidAmount;

    private String proposalDocument;

    private LocalDate submittedDate;

    private String status;

    @ManyToOne
    @JoinColumn(name = "tender_id")
    private Tender tender;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private User business;

    public Bid() {}

    public Long getId() { return id; }

    public Double getBidAmount() { return bidAmount; }
    public void setBidAmount(Double bidAmount) { this.bidAmount = bidAmount; }

    public String getProposalDocument() { return proposalDocument; }
    public void setProposalDocument(String proposalDocument) { this.proposalDocument = proposalDocument; }

    public LocalDate getSubmittedDate() { return submittedDate; }
    public void setSubmittedDate(LocalDate submittedDate) { this.submittedDate = submittedDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Tender getTender() { return tender; }
    public void setTender(Tender tender) { this.tender = tender; }

    public User getBusiness() { return business; }
    public void setBusiness(User business) { this.business = business; }
}