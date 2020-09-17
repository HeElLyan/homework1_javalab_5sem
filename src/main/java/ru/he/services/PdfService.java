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
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfService {
    private static final String DEST = "/Users/heel/IdeaProjects/3Course/javalab/homework/homework1_pec_documents/";

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

//    private String createDirectories(Client client) throws IOException {
//        String destDirectory = DEST + client.getId().toString();
//        Files.createDirectories(Paths.get(destDirectory));
//        return destDirectory;
//    }

//    private PdfFont getEnglishFont() {
//        try {
//            return PdfFontFactory.createFont(
//                    "src/main/resources/FreeSans.ttf",
//                    "CP1251", true);
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//    }

    private Document createTemplateForPdf(byte[] data) throws IOException {
        Client client = SerializationUtils.deserialize(data);

        String fileDest = DEST + client.getId().toString() + "/" + PdfType.ACADEMIC_VACATION.name() + ".pdf";
        PdfDocument pdf = new PdfDocument(new PdfWriter(fileDest));

        Document document = new Document(pdf, PageSize.A4, true);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        document.setFont(font);

        Paragraph paragraph1 = new Paragraph("EXCLUSION")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(20)
                .setBold();

        document.add(paragraph1);
        return document;
    }
    private void createAcademicPdf(byte[] data) throws IOException {
        Document document = createTemplateForPdf(data);
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
        Document document = createTemplateForPdf(data);
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
