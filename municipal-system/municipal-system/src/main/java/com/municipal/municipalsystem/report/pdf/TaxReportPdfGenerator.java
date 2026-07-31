package com.municipal.municipalsystem.report.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.municipal.municipalsystem.report.dto.TaxReportDTO;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TaxReportPdfGenerator {

    public static ByteArrayInputStream generate(List<TaxReportDTO> taxes) {

        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            // =========================
            // Fonts
            // =========================
            Font councilFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD, 20);
            councilFont.setColor(new Color(21, 101, 192));

            Font reportFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD, 16);
            reportFont.setColor(new Color(21, 101, 192));

            Font infoFont = FontFactory.getFont(
                    FontFactory.HELVETICA, 11);

            Font totalFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD, 12);

            // =========================
            // Header
            // =========================



            Paragraph council = new Paragraph(
                    "PUTTALAM MUNICIPAL COUNCIL",
                    councilFont);
            council.setAlignment(Element.ALIGN_CENTER);
            document.add(council);

            Paragraph report = new Paragraph(
                    "TAX COLLECTION REPORT",
                    reportFont);
            report.setAlignment(Element.ALIGN_CENTER);
            document.add(report);

            document.add(new Paragraph(" "));

            String reportNo = "TAX-" +
                    LocalDateTime.now().getYear() + "-001";

            String generatedDate = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));

            document.add(new Paragraph(
                    "Report No      : " + reportNo,
                    infoFont));

            document.add(new Paragraph(
                    "Generated Date : " + generatedDate,
                    infoFont));

            document.add(new Paragraph(
                    "--------------------------------------------------------------"));

            document.add(new Paragraph(" "));

            // =========================
            // Table
            // =========================
            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1, 3, 2, 2, 2, 2, 2, 2});

            addHeader(table, "ID");
            addHeader(table, "Citizen");
            addHeader(table, "Tax Type");
            addHeader(table, "Amount");
            addHeader(table, "Penalty");
            addHeader(table, "Total");
            addHeader(table, "Due Date");
            addHeader(table, "Status");

            double totalTax = 0;

            for (TaxReportDTO tax : taxes) {

                table.addCell(String.valueOf(tax.getTaxId()));
                table.addCell(tax.getCitizenName());
                table.addCell(tax.getTaxType());
                table.addCell(String.format("%.2f", tax.getAmount()));
                table.addCell(String.format("%.2f", tax.getPenalty()));
                table.addCell(String.format("%.2f", tax.getTotalAmount()));
                table.addCell(String.valueOf(tax.getDueDate()));
                table.addCell(tax.getStatus());

                totalTax += tax.getTotalAmount();
            }

            document.add(table);

            // =========================
            // Summary
            // =========================
            document.add(new Paragraph(" "));
            document.add(new Paragraph(
                    "--------------------------------------------------------------"));

            Paragraph totalRecords = new Paragraph(
                    "Total Records : " + taxes.size(),
                    totalFont);
            totalRecords.setAlignment(Element.ALIGN_RIGHT);
            document.add(totalRecords);

            Paragraph totalAmount = new Paragraph(
                    "Total Tax Amount : Rs. "
                            + String.format("%.2f", totalTax),
                    totalFont);
            totalAmount.setAlignment(Element.ALIGN_RIGHT);
            document.add(totalAmount);

            document.add(new Paragraph(" "));

            // =========================
            // Footer
            // =========================
            Paragraph footer = new Paragraph(
                    "************* END OF REPORT *************",
                    infoFont);
            footer.setAlignment(Element.ALIGN_CENTER);

            document.add(footer);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private static void addHeader(PdfPTable table, String title) {

        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(new Color(21, 101, 192));

        Font headerFont = FontFactory.getFont(
                FontFactory.HELVETICA_BOLD);
        headerFont.setColor(Color.WHITE);

        cell.setPhrase(new Phrase(title, headerFont));

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(8);

        table.addCell(cell);
    }
}