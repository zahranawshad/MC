package com.municipal.municipalsystem.report.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.municipal.municipalsystem.report.dto.TenderReportDTO;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TenderReportPdfGenerator {

    public static ByteArrayInputStream generate(List<TenderReportDTO> tenders) {

        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            Font councilFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD, 20);
            councilFont.setColor(new Color(21,101,192));

            Font reportFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,16);
            reportFont.setColor(new Color(21,101,192));

            Font infoFont = FontFactory.getFont(
                    FontFactory.HELVETICA,11);

            Font totalFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,12);

            Paragraph council = new Paragraph(
                    "PUTTALAM MUNICIPAL COUNCIL",
                    councilFont);

            council.setAlignment(Element.ALIGN_CENTER);
            document.add(council);

            Paragraph report = new Paragraph(
                    "TENDER REPORT",
                    reportFont);

            report.setAlignment(Element.ALIGN_CENTER);
            document.add(report);

            document.add(new Paragraph(" "));

            document.add(new Paragraph(
                    "Generated Date : " +
                            LocalDateTime.now().format(
                                    DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                    infoFont));

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(6);

            table.setWidthPercentage(100);

            table.setWidths(new int[]{1,4,2,2,2,2});

            addHeader(table,"ID");
            addHeader(table,"Title");
            addHeader(table,"Budget");
            addHeader(table,"Closing Date");
            addHeader(table,"Status");
            addHeader(table,"Total Bids");

            double totalBudget = 0;

            for(TenderReportDTO tender : tenders){

                table.addCell(String.valueOf(tender.getTenderId()));
                table.addCell(tender.getTitle());
                table.addCell(String.format("%.2f",
                        tender.getEstimatedBudget()));
                table.addCell(String.valueOf(
                        tender.getClosingDate()));
                table.addCell(tender.getStatus());
                table.addCell(String.valueOf(
                        tender.getTotalBids()));

                totalBudget += tender.getEstimatedBudget();

            }

            document.add(table);

            document.add(new Paragraph(" "));

            Paragraph total1 = new Paragraph(
                    "Total Tenders : " + tenders.size(),
                    totalFont);

            total1.setAlignment(Element.ALIGN_RIGHT);

            document.add(total1);

            Paragraph total2 = new Paragraph(
                    "Total Estimated Budget : Rs. "
                            + String.format("%.2f", totalBudget),
                    totalFont);

            total2.setAlignment(Element.ALIGN_RIGHT);

            document.add(total2);

            document.add(new Paragraph(" "));

            Paragraph footer = new Paragraph(
                    "************* END OF REPORT *************");

            footer.setAlignment(Element.ALIGN_CENTER);

            document.add(footer);

            document.close();

        } catch (Exception e){

            e.printStackTrace();

        }

        return new ByteArrayInputStream(out.toByteArray());

    }

    private static void addHeader(PdfPTable table,String title){

        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(new Color(21,101,192));

        Font font = FontFactory.getFont(
                FontFactory.HELVETICA_BOLD);

        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase(title,font));

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        cell.setPadding(8);

        table.addCell(cell);

    }

}