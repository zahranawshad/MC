package com.municipal.municipalsystem.tender.dto;

public class BidRequest {

    private Double bidAmount;
    private String proposalDocument;

    public Double getBidAmount() { return bidAmount; }
    public void setBidAmount(Double bidAmount) { this.bidAmount = bidAmount; }

    public String getProposalDocument() { return proposalDocument; }
    public void setProposalDocument(String proposalDocument) { this.proposalDocument = proposalDocument; }
}