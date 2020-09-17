import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.io.FileNotFoundException;

public class Main {
    private static final String DEST = "/Users/heel/Downloads/javalab-3-master/HW/rabbit1/pdf/";

    public static void main(String[] args) throws FileNotFoundException {
        PdfDocument pdf = new PdfDocument(new
                PdfWriter(DEST + "prog.pdf"));
        Document document = new Document(pdf, PageSize.A4, true);
        document.add(new Paragraph(new Text("metal")));
        document.close();
    }
}
