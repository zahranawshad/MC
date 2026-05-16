package com.municipal.municipalsystem.tax;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taxes")
public class TaxController {

    private final TaxService taxService;

    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    // 🔹 Get logged-in user's taxes
    @GetMapping("/my")
    public List<Tax> getMyTaxes(Authentication authentication) {

        String username = authentication.getName();

        return taxService.getCurrentUserTaxes(username);
    }
}