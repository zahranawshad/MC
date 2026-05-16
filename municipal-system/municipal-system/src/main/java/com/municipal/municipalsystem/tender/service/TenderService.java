package com.municipal.municipalsystem.tender.service;

import com.municipal.municipalsystem.tender.entity.*;
import com.municipal.municipalsystem.tender.repository.*;
import com.municipal.municipalsystem.user.User;
import com.municipal.municipalsystem.user.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
@Service
public class TenderService {

    private final TenderRepository tenderRepository;
    private final BidRepository bidRepository;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    private final TenderAwardRepository tenderAwardRepository;
    public TenderService(
            TenderRepository tenderRepository,
            BidRepository bidRepository,
            UserRepository userRepository,
            TenderAwardRepository tenderAwardRepository,
            FileStorageService fileStorageService) {

        this.fileStorageService = fileStorageService;
        this.tenderRepository = tenderRepository;
        this.bidRepository = bidRepository;
        this.userRepository = userRepository;
        this.tenderAwardRepository = tenderAwardRepository;
    }

    public Tender createTender(Tender tender) {

        tender.setStatus("OPEN");

        return tenderRepository.save(tender);
    }

    public List<Tender> getAllTenders() {

        return tenderRepository.findAll();
    }


    public List<Bid> getBids(Long tenderId) {

        return bidRepository.findByTenderId(tenderId);
    }

    public TenderAward awardTender(Long bidId) {

        Bid selectedBid = bidRepository.findById(bidId)
                .orElseThrow(() -> new RuntimeException("Bid not found"));

        Tender tender = selectedBid.getTender();

        // mark selected bid
        selectedBid.setStatus("ACCEPTED");
        bidRepository.save(selectedBid);

        // reject others
        List<Bid> allBids = bidRepository.findByTender(tender);

        for (Bid b : allBids) {
            if (!b.getId().equals(bidId)) {
                b.setStatus("REJECTED");
                bidRepository.save(b);
            }
        }

        // update tender
        tender.setStatus("AWARDED");
        tenderRepository.save(tender);

        // create award record
        TenderAward award = new TenderAward();
        award.setBid(selectedBid);
        award.setAwardDate(LocalDate.now());

        return tenderAwardRepository.save(award);
    }
    public Bid submitBid(Long tenderId,
                         String username,
                         Double bidAmount,
                         MultipartFile file) {

        Tender tender = tenderRepository.findById(tenderId)
                .orElseThrow(() -> new RuntimeException("Tender not found"));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path uploadPath = Paths.get("uploads/");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Files.write(uploadPath.resolve(fileName), file.getBytes());

            Bid bid = new Bid();
            bid.setBidAmount(bidAmount);
            bid.setProposalDocument(fileName);
            bid.setSubmittedDate(LocalDate.now());
            bid.setStatus("SUBMITTED");

            bid.setTender(tender);
            bid.setBusiness(user);

            return bidRepository.save(bid);

        } catch (Exception e) {
            throw new RuntimeException("File upload failed");
        }
    }

    public List<Bid> getBusinessBids(String username){

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return bidRepository.findByBusiness(user);
    }
}