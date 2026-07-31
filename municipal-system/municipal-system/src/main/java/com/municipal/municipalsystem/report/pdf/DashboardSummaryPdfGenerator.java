package com.municipal.municipalsystem.report.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.municipal.municipalsystem.report.dto.DashboardReportDTO;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardSummaryPdfGenerator {

    public static ByteArrayInputStream generate(DashboardReportDTO report) {

        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            Font councilFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD, 20);
            councilFont.setColor(new Color(21,101,192));

            Font titleFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,16);
            titleFont.setColor(new Color(21,101,192));

            Font headingFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,13);

            Font normalFont = FontFactory.getFont(
                    FontFactory.HELVETICA,11);

            Paragraph council = new Paragraph(
                    "PUTTALAM MUNICIPAL COUNCIL",
                    councilFont);
            council.setAlignment(Element.ALIGN_CENTER);
            document.add(council);

            Paragraph title = new Paragraph(
                    "MUNICIPAL SYSTEM DASHBOARD REPORT",
                    titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));

            document.add(new Paragraph(
                    "Generated Date : " +
                            LocalDateTime.now().format(
                                    DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                    normalFont));

            document.add(new Paragraph(" "));

            // USERS
            document.add(new Paragraph("USER MANAGEMENT", headingFont));
            document.add(new Paragraph("Total Users : " + report.getTotalUsers(), normalFont));
            document.add(new Paragraph("Citizens : " + report.getTotalCitizens(), normalFont));
            document.add(new Paragraph("Businesses : " + report.getTotalBusinesses(), normalFont));
            document.add(new Paragraph("Administrators : " + report.getTotalAdmins(), normalFont));

            document.add(new Paragraph(" "));

            // TAXES
            document.add(new Paragraph("TAX MANAGEMENT", headingFont));
            document.add(new Paragraph("Total Taxes : " + report.getTotalTaxes(), normalFont));
            document.add(new Paragraph("Paid Taxes : " + report.getPaidTaxes(), normalFont));
            document.add(new Paragraph("Pending Taxes : " + report.getPendingTaxes(), normalFont));
            document.add(new Paragraph("Overdue Taxes : " + report.getOverdueTaxes(), normalFont));
            document.add(new Paragraph(
                    "Tax Revenue : Rs. " +
                            String.format("%.2f", report.getTotalTaxRevenue()),
                    normalFont));

            document.add(new Paragraph(" "));

            // GARBAGE
            document.add(new Paragraph("GARBAGE MANAGEMENT", headingFont));
            document.add(new Paragraph("Total Garbage Fees : " + report.getTotalGarbageFees(), normalFont));
            document.add(new Paragraph("Paid Fees : " + report.getPaidGarbageFees(), normalFont));
            document.add(new Paragraph("Pending Fees : " + report.getPendingGarbageFees(), normalFont));
            document.add(new Paragraph(
                    "Garbage Revenue : Rs. " +
                            String.format("%.2f", report.getTotalGarbageRevenue()),
                    normalFont));

            document.add(new Paragraph(" "));

            // TENDERS
            document.add(new Paragraph("TENDER MANAGEMENT", headingFont));
            document.add(new Paragraph("Total Tenders : " + report.getTotalTenders(), normalFont));
            document.add(new Paragraph("Open Tenders : " + report.getOpenTenders(), normalFont));
            document.add(new Paragraph("Awarded Tenders : " + report.getAwardedTenders(), normalFont));
            document.add(new Paragraph("Closed Tenders : " + report.getClosedTenders(), normalFont));
            document.add(new Paragraph("Total Bids : " + report.getTotalBids(), normalFont));

            document.add(new Paragraph(" "));

            Paragraph footer = new Paragraph(
                    "************* END OF REPORT *************",
                    normalFont);

            footer.setAlignment(Element.ALIGN_CENTER);

            document.add(footer);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}