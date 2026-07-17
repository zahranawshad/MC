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

    // =========================
    // CREATE TAX
    // =========================
    public Tax createTax(Long userId, TaxType taxType, Double amount, LocalDate dueDate) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Tax tax = new Tax();
        tax.setUser(user);
        tax.setTaxType(taxType);
        tax.setAmount(amount);
        tax.setDueDate(dueDate);

        tax.setPenalty(0.0);
        tax.setTotalAmount(amount);
        tax.setStatus(TaxStatus.PENDING);

        return taxRepository.save(tax);
    }

    // =========================
    // GET ALL TAXES (ADMIN)
    // =========================
    public List<Tax> getAllTaxes() {

        updatePenaltiesAndStatus();

        return taxRepository.findAll();
    }

    // =========================
    // GET TAXES BY USER ID
    // =========================
    public List<Tax> getTaxesByUser(Long userId) {

        updatePenaltiesAndStatus();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return taxRepository.findByUser(user);
    }

    // =========================
    // GET CURRENT USER TAXES
    // =========================
    public List<Tax> getCurrentUserTaxes(String username) {

        updatePenaltiesAndStatus();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return taxRepository.findByUser(user);
    }

    // =========================
    // UPDATE PENALTIES
    // =========================
    public void updatePenaltiesAndStatus() {

        List<Tax> taxes = taxRepository.findAll();

        LocalDate today = LocalDate.now();

        for (Tax tax : taxes) {

            // Never modify paid taxes
            if (tax.getStatus() == TaxStatus.PAID) {
                continue;
            }

            if (today.isAfter(tax.getDueDate())) {

                double penalty = tax.getAmount() * 0.10;

                tax.setPenalty(penalty);
                tax.setTotalAmount(tax.getAmount() + penalty);
                tax.setStatus(TaxStatus.OVERDUE);

            } else {

                tax.setPenalty(0.0);
                tax.setTotalAmount(tax.getAmount());
                tax.setStatus(TaxStatus.PENDING);

            }

            taxRepository.save(tax);
        }
    }

    // =========================
    // PAY TAX
    // =========================
    public TaxPayment payTax(Long taxId, String paymentMethod) {

        updatePenaltiesAndStatus();

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

}