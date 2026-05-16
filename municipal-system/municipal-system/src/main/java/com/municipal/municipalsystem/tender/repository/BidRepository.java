package com.municipal.municipalsystem.tender.repository;

import com.municipal.municipalsystem.tender.entity.Bid;
import com.municipal.municipalsystem.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.List;
import com.municipal.municipalsystem.tender.entity.Tender;


public interface BidRepository extends JpaRepository<Bid, Long> {

    List<Bid> findByTenderId(Long tenderId);
    List<Bid> findByTender(Tender tender);

    List<Bid> findByBusiness(User business);

}
