package com.sda.cats.service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPHeaderCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sda.cats.model.Cat;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CatPdfGenerator {


    public ByteArrayOutputStream generateCatTable(List<Cat> cats) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);
            document.open();
            //tworzymy obiekt tabeli
            PdfPTable table = new PdfPTable(4);
            //dodajemy naglowki
            setColumnHeaders(table);
            //dodajemy dane
            cats.forEach(i -> addRow(table, i));

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }


    private void addRow(PdfPTable table, Cat i) {
        table.addCell(i.getId().toString());
        table.addCell(i.getName());
        table.addCell(i.getGender().toString());
        table.addCell(i.getTailLength().toString());
    }

    private void setColumnHeaders(PdfPTable table) {

        Stream.of("id", "name", "gender", "tailLength").forEach(header -> {
            PdfPHeaderCell headerCell = new PdfPHeaderCell();
            headerCell.setBackgroundColor(BaseColor.GREEN);
            headerCell.setBorderColor(BaseColor.RED);
            headerCell.setBorderWidth(2.0f);
            headerCell.setPhrase(new Phrase(header));
            table.addCell(headerCell);
        });


    }

}
