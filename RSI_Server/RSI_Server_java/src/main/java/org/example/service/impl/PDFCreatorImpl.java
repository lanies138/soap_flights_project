package org.example.service.impl;

import static org.example.utils.DateUtils.SDF;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Date;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.reservations.Ticket;
import org.example.service.PDFCreator;
import org.springframework.stereotype.Component;

@Component
public class PDFCreatorImpl implements PDFCreator {
    
    private static final String BASE_DIR = "RSI_Server_java/src/main/resources/generated-tickets-pdfs";
    
    @Override
    public File create(Ticket ticket) {
        String fileName = String.format("Booked_ticket_%d.pdf", ticket.getId());
        Path location = Paths.get(BASE_DIR, fileName);
        Document document = new Document();
        
        try {
            File outputFile = new File(location.toString());
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFile));
            document.open();
            
            document.add(new Paragraph("Ticket bought by  " + ticket.getPassengerName()));
            document.add(Chunk.NEWLINE);
            
            PdfPTable table = new PdfPTable(6);
            table.setTotalWidth(1200);
            table.setWidths(new int[]{3, 5, 5, 5, 5, 4});
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            appendTableHeader(table);
            appendTicketDetails(table, ticket);
            
            document.add(table);
            
            document.close();
            writer.close();
        } catch (DocumentException | FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        return new File(location.toString());
    }
    
    private void appendTableHeader(PdfPTable table) {
        PdfPCell cell;
        cell = new PdfPCell();
        cell.addElement(new Paragraph("Id"));
        table.addCell(cell);
        cell = new PdfPCell();
        cell.addElement(new Paragraph("Price in €"));
        table.addCell(cell);
        cell = new PdfPCell();
        cell.addElement(new Paragraph("From City"));
        table.addCell(cell);
        cell = new PdfPCell();
        cell.addElement(new Paragraph("To City"));
        table.addCell(cell);
        cell = new PdfPCell();
        cell.addElement(new Paragraph("Date"));
        table.addCell(cell);
        cell = new PdfPCell();
        cell.addElement(new Paragraph("Time"));
        table.addCell(cell);
    }
    
    private void appendTicketDetails(PdfPTable table, Ticket ticket) {
        PdfPCell cell;
        cell = new PdfPCell();
        cell.addElement(new Paragraph(String.valueOf(ticket.getId())));
        table.addCell(cell);
        cell = new PdfPCell();
        cell.addElement(new Paragraph(String.valueOf(ticket.getPrice())));
        table.addCell(cell);
        cell = new PdfPCell();
        cell.addElement(new Paragraph(ticket.getFlight().getFromCity()));
        table.addCell(cell);
        cell = new PdfPCell();
        cell.addElement(new Paragraph(ticket.getFlight().getToCity()));
        table.addCell(cell);
        cell = new PdfPCell();
        cell.addElement(new Paragraph(ticket.getFlight().getDate()));
        table.addCell(cell);
        cell = new PdfPCell();
        cell.addElement(new Paragraph(ticket.getFlight().getTime()));
        table.addCell(cell);
    }
}
