package com.municipal.municipalsystem.tax;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaxScheduler {

    private final TaxService taxService;

    public TaxScheduler(TaxService taxService) {
        this.taxService = taxService;
    }

    // Runs every day at midnight
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateTaxes() {
        taxService.updatePenaltiesAndStatus();
    }
}