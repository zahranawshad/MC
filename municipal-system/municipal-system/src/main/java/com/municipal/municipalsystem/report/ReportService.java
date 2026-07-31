package com.municipal.municipalsystem.report;

import com.municipal.municipalsystem.report.dto.TaxReportDTO;
import com.municipal.municipalsystem.tax.Tax;
import com.municipal.municipalsystem.tax.TaxRepository;
import org.springframework.stereotype.Service;
import com.municipal.municipalsystem.tax.TaxStatus;

import com.municipal.municipalsystem.garbage.GarbageFee;
import com.municipal.municipalsystem.garbage.repository.GarbageFeeRepository;
import com.municipal.municipalsystem.report.dto.GarbageFeeReportDTO;

import com.municipal.municipalsystem.tender.entity.Tender;
import com.municipal.municipalsystem.tender.repository.TenderRepository;
import com.municipal.municipalsystem.tender.repository.BidRepository;
import com.municipal.municipalsystem.report.dto.TenderReportDTO;

import com.municipal.municipalsystem.report.dto.DashboardReportDTO;
import com.municipal.municipalsystem.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

import com.municipal.municipalsystem.tax.TaxStatus;
import java.util.Arrays;

@Service
public class ReportService {

    private final TaxRepository taxRepository;
    private final GarbageFeeRepository garbageFeeRepository;
    private final TenderRepository tenderRepository;
    private final BidRepository bidRepository;
    private final UserRepository userRepository;

    public ReportService(
            TaxRepository taxRepository,
            GarbageFeeRepository garbageFeeRepository,
            TenderRepository tenderRepository,
            BidRepository bidRepository,
            UserRepository userRepository) {

        this.taxRepository = taxRepository;
        this.garbageFeeRepository = garbageFeeRepository;
        this.tenderRepository = tenderRepository;
        this.bidRepository = bidRepository;
        this.userRepository = userRepository;
    }
    public List<TaxReportDTO> getTaxReport() {

        List<Tax> taxes = taxRepository.findAll();

        List<TaxReportDTO> report = new ArrayList<>();

        for (Tax tax : taxes) {

            TaxReportDTO dto = new TaxReportDTO();

            dto.setTaxId(tax.getId());
            dto.setCitizenName(tax.getUser().getFullName());
            dto.setTaxType(tax.getTaxType().name());
            dto.setAmount(tax.getAmount());
            dto.setPenalty(tax.getPenalty());
            dto.setTotalAmount(tax.getTotalAmount());
            dto.setDueDate(tax.getDueDate());
            dto.setStatus(tax.getStatus().name());

            //dto.setPhone(tax.getUser().getPhone());

            report.add(dto);
        }

        return report;
    }

    public List<TaxReportDTO> getOutstandingTaxReport() {

        List<Tax> taxes = taxRepository.findByStatusIn(
                Arrays.asList(
                        TaxStatus.PENDING,
                        TaxStatus.OVERDUE
                )
        );





        List<TaxReportDTO> report = new ArrayList<>();

        for (Tax tax : taxes) {

            TaxReportDTO dto = new TaxReportDTO();

            dto.setTaxId(tax.getId());
            dto.setCitizenName(tax.getUser().getFullName());
            dto.setTaxType(tax.getTaxType().name());
            dto.setAmount(tax.getAmount());
            dto.setPenalty(tax.getPenalty());
            dto.setTotalAmount(tax.getTotalAmount());
            dto.setDueDate(tax.getDueDate());
            dto.setStatus(tax.getStatus().name());

            report.add(dto);
        }

        return report;
    }

    public List<GarbageFeeReportDTO> getGarbageFeeReport() {

        List<GarbageFee> fees = garbageFeeRepository.findAll();

        List<GarbageFeeReportDTO> report = new ArrayList<>();

        for (GarbageFee fee : fees) {

            GarbageFeeReportDTO dto = new GarbageFeeReportDTO();

            dto.setFeeId(fee.getId());
            dto.setCitizenName(fee.getCitizen().getFullName());
            dto.setArea(fee.getCitizen().getArea());
            dto.setWeeklyKg(fee.getWeeklyKg());
            dto.setYearlyKg(fee.getYearlyKg());
            dto.setRatePerKg(fee.getRatePerKg());
            dto.setAmount(fee.getAmount());
            dto.setDueDate(fee.getDueDate());
            dto.setStatus(fee.getStatus());

            report.add(dto);
        }

        return report;
    }


    public List<TenderReportDTO> getTenderReport() {

        List<Tender> tenders = tenderRepository.findAll();

        List<TenderReportDTO> report = new ArrayList<>();

        for (Tender tender : tenders) {

            TenderReportDTO dto = new TenderReportDTO();

            dto.setTenderId(tender.getId());
            dto.setTitle(tender.getTitle());
            dto.setEstimatedBudget(tender.getEstimatedBudget());
            dto.setClosingDate(tender.getClosingDate());
            dto.setStatus(tender.getStatus());

            dto.setTotalBids(
                    (int) bidRepository.countByTender(tender));

            report.add(dto);
        }

        return report;
    }

    public DashboardReportDTO getDashboardSummaryReport() {

        DashboardReportDTO dto = new DashboardReportDTO();

        // Users
        dto.setTotalUsers(userRepository.count());
        dto.setTotalCitizens(userRepository.countByRoles_Name("ROLE_CITIZEN"));
        dto.setTotalBusinesses(userRepository.countByRoles_Name("ROLE_BUSINESS"));
        dto.setTotalAdmins(userRepository.countByRoles_Name("ROLE_ADMIN"));

        // Taxes
        dto.setTotalTaxes(taxRepository.count());
        dto.setPaidTaxes(taxRepository.countByStatus(TaxStatus.PAID));
        dto.setPendingTaxes(taxRepository.countByStatus(TaxStatus.PENDING));
        dto.setOverdueTaxes(taxRepository.countByStatus(TaxStatus.OVERDUE));

        double taxRevenue = 0;

        for (Tax tax : taxRepository.findByStatus(TaxStatus.PAID)) {
            taxRevenue += tax.getTotalAmount();
        }

        dto.setTotalTaxRevenue(taxRevenue);

        // Garbage Fees
        dto.setTotalGarbageFees(garbageFeeRepository.count());
        dto.setPaidGarbageFees(garbageFeeRepository.countByStatus("PAID"));
        dto.setPendingGarbageFees(garbageFeeRepository.countByStatus("PENDING"));

        double garbageRevenue = 0;

        for (GarbageFee fee : garbageFeeRepository.findByStatus("PAID")) {
            garbageRevenue += fee.getAmount();
        }

        dto.setTotalGarbageRevenue(garbageRevenue);

        // Tenders
        dto.setTotalTenders(tenderRepository.count());
        dto.setOpenTenders(tenderRepository.countByStatus("OPEN"));
        dto.setAwardedTenders(tenderRepository.countByStatus("AWARDED"));
        dto.setClosedTenders(tenderRepository.countByStatus("CLOSED"));

        dto.setTotalBids(bidRepository.count());

        return dto;
    }
}