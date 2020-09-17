package ru.he.services;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import org.apache.commons.lang3.SerializationUtils;
import ru.he.enums.PdfType;
import ru.he.models.Client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfService {
    private static final String DEST = "/Users/heel/IdeaProjects/3Course/javalab/homework/homework1_pec_documents/pdf/";

    public void createPdf(byte[] data, PdfType pdfType) throws IOException {
        switch (pdfType) {
            case ACADEMIC_VACATION:
                createAcademicPdf(data);
                break;
            case EXCLUSION:
                createExclusionPdf(data);
                break;
        }
    }

    private Document createTemplateForPdf(byte[] data, PdfType pdfType) throws IOException {
        Client client = SerializationUtils.deserialize(data);

        String fileDest = DEST + client.getId().toString() + "/";
        Files.createDirectories(Paths.get(fileDest));
        fileDest+=pdfType.name() + ".pdf";

        PdfDocument pdf = new PdfDocument(new PdfWriter(fileDest));

        Document document = new Document(pdf, PageSize.A4, true);

//        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);

        Paragraph paragraph1 = new Paragraph("EXCLUSION")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(20)
                .setBold();

        return document.add(paragraph1);
    }
    private void createAcademicPdf(byte[] data) throws IOException {
        Document document = createTemplateForPdf(data, PdfType.ACADEMIC_VACATION);
        Client client = SerializationUtils.deserialize(data);

        String text = "I," + client.getName() + " " + client.getLastName() + ", want to take an academic year," +
                "because I'm tired of coming there, meeting all this people at once. My passport data: " + client.getPassportNumber() +
                ", the date of issue: " + client.getPassportDate() + ", age: " + client.getAge() + ". So plz leave me alone!!!";
        Paragraph paragraph2 = new Paragraph(text);
        document.add(paragraph2);

        document.add(new Paragraph("Date: " + new SimpleDateFormat("dd.MM.yyyy").format(new Date())));

        document.close();
    }

    private void createExclusionPdf(byte[] data) throws IOException {
        Document document = createTemplateForPdf(data, PdfType.EXCLUSION);
        Client client = SerializationUtils.deserialize(data);

        String text = "I," + client.getName() + " " + client.getLastName() + ", want to fulfill my dreams before I die!" +
                " My passport data: " + client.getPassportNumber() + ", the date of issue: " + client.getPassportDate() +
                ", age: " + client.getAge() + ". So plz let me do it!!!";
        Paragraph paragraph2 = new Paragraph(text);
        document.add(paragraph2);

        document.add(new Paragraph("Date: " + new SimpleDateFormat("dd.MM.yyyy").format(new Date())));

        document.close();
    }
}
