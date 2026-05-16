package com.municipal.municipalsystem.tender.controller;

import com.municipal.municipalsystem.tender.dto.*;
import com.municipal.municipalsystem.tender.entity.*;
import com.municipal.municipalsystem.tender.service.TenderService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import com.municipal.municipalsystem.tender.repository.TenderRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/tenders")
public class TenderController {

    private final TenderRepository tenderRepository;

    private final TenderService tenderService;

    public TenderController(TenderService tenderService,
                            TenderRepository tenderRepository) {

        this.tenderService = tenderService;
        this.tenderRepository = tenderRepository;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Tender createTender(@RequestBody TenderRequest request) {

        Tender tender = new Tender();

        tender.setTitle(request.getTitle());
        tender.setDescription(request.getDescription());
        tender.setEstimatedBudget(request.getEstimatedBudget());
        tender.setClosingDate(request.getClosingDate());

        return tenderService.createTender(tender);
    }

    @GetMapping
    public List<Tender> getAllTenders() {

        return tenderService.getAllTenders();
    }

    @PostMapping("/{tenderId}/bid")
    @PreAuthorize("hasRole('BUSINESS')")
    public ResponseEntity<?> submitBid(
            @PathVariable Long tenderId,
            @RequestParam("bidAmount") Double bidAmount,
            @RequestParam("proposal") MultipartFile file,
            Authentication authentication) {
        System.out.println("🔥 BID API HIT");
        try {
            String username = authentication.getName();

            Bid bid = tenderService.submitBid(
                    tenderId,
                    username,
                    bidAmount,
                    file
            );

            return ResponseEntity.ok(bid);

        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
    @GetMapping("/{tenderId}/bids")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Bid> getBids(@PathVariable Long tenderId) {

        return tenderService.getBids(tenderId);
    }

    @PostMapping("/award/{bidId}")
    @PreAuthorize("hasRole('ADMIN')")
    public TenderAward awardTender(@PathVariable Long bidId) {

        return tenderService.awardTender(bidId);
    }



    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @RequestParam String status){

        Tender tender = tenderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tender not found"));

        tender.setStatus(status);

        tenderRepository.save(tender);
        return ResponseEntity.ok(tender);
    }

    @GetMapping("/test-auth")
    public String test(Authentication auth) {
        return auth.getAuthorities().toString();
    }

    @GetMapping("/my-bids")
    @PreAuthorize("hasRole('BUSINESS')")
    public List<Bid> myBids(Authentication authentication){

        String username = authentication.getName();

        return tenderService.getBusinessBids(username);
    }



}
