package com.municipal.municipalsystem.tax;

import com.municipal.municipalsystem.user.User;
import com.municipal.municipalsystem.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaxService {

    private final TaxRepository taxRepository;
    private final UserRepository userRepository;
    private final TaxPaymentRepository taxPaymentRepository;

    public TaxService(TaxRepository taxRepository,
                      UserRepository userRepository,
                      TaxPaymentRepository taxPaymentRepository) {
        this.taxRepository = taxRepository;
        this.userRepository = userRepository;
        this.taxPaymentRepository = taxPaymentRepository;
    }

    // 🔹 Create and Assign Tax
    public Tax createTax(Long userId, TaxType taxType, Double amount, LocalDate dueDate) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Tax tax = new Tax();
        tax.setUser(user);
        tax.setTaxType(taxType);
        tax.setAmount(amount);
        tax.setDueDate(dueDate);

        // penalty initially 0
        tax.setPenalty(0.0);

        // total amount
        tax.setTotalAmount(amount);

        tax.setStatus(TaxStatus.PENDING);

        return taxRepository.save(tax);
    }

    // 🔹 Get all taxes (Admin)
    public List<Tax> getAllTaxes() {
        return taxRepository.findAll();
    }

    // 🔹 Get taxes of a specific user
    public List<Tax> getTaxesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return taxRepository.findByUser(user);
    }

    public void updatePenaltiesAndStatus() {
        List<Tax> taxes = taxRepository.findAll();

        LocalDate today = LocalDate.now();

        for (Tax tax : taxes) {
            if (tax.getStatus() == TaxStatus.PENDING && today.isAfter(tax.getDueDate())) {
                double penalty = tax.getAmount() * 0.1; // 10% penalty
                tax.setPenalty(penalty);
                tax.setTotalAmount(tax.getAmount() + penalty);
                tax.setStatus(TaxStatus.OVERDUE);

                taxRepository.save(tax);
            }
        }
    }

    public TaxPayment payTax(Long taxId, String paymentMethod) {

        Tax tax = taxRepository.findById(taxId)
                .orElseThrow(() -> new RuntimeException("Tax not found"));

        if (tax.getStatus() == TaxStatus.PAID) {
            throw new RuntimeException("Tax already paid");
        }

        tax.setStatus(TaxStatus.PAID);
        taxRepository.save(tax);

        TaxPayment payment = new TaxPayment();
        payment.setTax(tax);
        payment.setPaymentMethod(paymentMethod);

        return taxPaymentRepository.save(payment);
    }

    public List<Tax> getCurrentUserTaxes(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return taxRepository.findByUser(user);
    }

}