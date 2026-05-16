package com.municipal.municipalsystem.tax;

import com.municipal.municipalsystem.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxRepository extends JpaRepository<Tax, Long> {

    List<Tax> findByUser(User user);
}