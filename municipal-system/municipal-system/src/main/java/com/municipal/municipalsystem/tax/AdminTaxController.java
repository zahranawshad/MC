package com.municipal.municipalsystem.tax;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin/taxes")
@PreAuthorize("hasRole('ADMIN')")
public class AdminTaxController {

    private final TaxService taxService;

    public AdminTaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    // Create tax
    @PostMapping
    public Tax createTax(
            @RequestParam Long userId,
            @RequestParam TaxType taxType,
            @RequestParam Double amount,
            @RequestParam String dueDate
    ) {

        return taxService.createTax(
                userId,
                taxType,
                amount,
                LocalDate.parse(dueDate)
        );
    }

    // 🔹 Get all taxes
    @GetMapping
    public List<Tax> getAllTaxes() {
        return taxService.getAllTaxes();
    }

    @PutMapping("/{taxId}/pay")
    public TaxPayment payTax(
            @PathVariable Long taxId,
            @RequestParam String paymentMethod
    ) {
        return taxService.payTax(taxId, paymentMethod);
    }
}