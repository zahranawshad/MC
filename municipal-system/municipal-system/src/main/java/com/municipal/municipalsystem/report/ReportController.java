package com.municipal.municipalsystem.report;

import com.municipal.municipalsystem.report.dto.GarbageFeeReportDTO;
import com.municipal.municipalsystem.report.dto.TaxReportDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.municipal.municipalsystem.report.pdf.TaxReportPdfGenerator;

import com.municipal.municipalsystem.report.pdf.GarbageFeeReportPdfGenerator;

import com.municipal.municipalsystem.report.pdf.TenderReportPdfGenerator;

import com.municipal.municipalsystem.report.pdf.DashboardSummaryPdfGenerator;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/tax")
    public List<TaxReportDTO> getTaxReport() {
        return reportService.getTaxReport();
    }

    @GetMapping("/tax/pdf")
    public ResponseEntity<InputStreamResource> downloadTaxReport(){

        ByteArrayInputStream pdf =
                TaxReportPdfGenerator.generate(reportService.getTaxReport());

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition",
                "inline; filename=Tax_Report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));

    }

    @GetMapping("/tax/outstanding/pdf")
    public ResponseEntity<InputStreamResource> downloadOutstandingTaxReport() {

        ByteArrayInputStream pdf =
                TaxReportPdfGenerator.generate(
                        reportService.getOutstandingTaxReport()
                );

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition",
                "inline; filename=Outstanding_Tax_Report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }

    @GetMapping("/garbage-fees")
    public String testGarbageReport() {

        System.out.println(">>> Garbage report endpoint reached <<<");

        return "OK";
    }
    @GetMapping("/garbage-fees/pdf")
    public ResponseEntity<InputStreamResource> downloadGarbageFeeReport() {

        ByteArrayInputStream pdf =
                GarbageFeeReportPdfGenerator.generate(
                        reportService.getGarbageFeeReport());

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition",
                "inline; filename=Garbage_Fee_Report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }

    @GetMapping("/tenders/pdf")
    public ResponseEntity<InputStreamResource> downloadTenderReport() {

        ByteArrayInputStream pdf =
                TenderReportPdfGenerator.generate(
                        reportService.getTenderReport());

        HttpHeaders headers = new HttpHeaders();

        headers.add(
                "Content-Disposition",
                "inline; filename=Tender_Report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));

    }

    @GetMapping("/dashboard/pdf")
    public ResponseEntity<InputStreamResource> downloadDashboardReport() {

        ByteArrayInputStream pdf =
                DashboardSummaryPdfGenerator.generate(
                        reportService.getDashboardSummaryReport());

        HttpHeaders headers = new HttpHeaders();

        headers.add(
                "Content-Disposition",
                "inline; filename=Dashboard_Summary_Report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }
}