package com.website.system.invoice;

import com.website.system.order.product.OrderProduct;
import com.website.system.product.datamodel.Product;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

@Service
public class InvoicePdfSaver {

    public void saveInvoiceToPdf(Invoice invoice, String filePath) {
        Set<OrderProduct> invoiceProducts = invoice.getProducts();

        File fontFile = new File("src/main/resources/fonts/DejaVuSans-Bold.ttf");

        try(PDDocument document = new PDDocument()){
            PDPage page = new PDPage();
            document.addPage(page);

            PDType0Font font = PDType0Font.load(document, fontFile);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(font, 12);
            contentStream.newLineAtOffset(100, 700);

            contentStream.showText("Invoice No: "+invoice.getInvoiceNumber());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Issue Date: "+invoice.getIssueDate());
            contentStream.newLineAtOffset(0,-15);
            contentStream.showText("Client: "+invoice.getClient().getFirstName() +" "+invoice.getClient().getLastName());
            contentStream.newLineAtOffset(0,-15);
            contentStream.showText("Products:");
            contentStream.newLineAtOffset(0,-15);

            int lineNumber = 1;
            for (OrderProduct invoiceProduct : invoiceProducts) {
                contentStream.showText(lineNumber + ". "+invoiceProduct.getProduct().getName()+
                        " "+invoiceProduct.getPrice()+" PLN, ilość: "+invoiceProduct.getQuantity());
                contentStream.newLineAtOffset(0,-15);
                lineNumber++;
            }

            contentStream.newLineAtOffset(0,-30);
            contentStream.showText(String.format("Gross total: %.2f PLN", invoice.getGrossTotal()));
            contentStream.newLineAtOffset(0,-15);

            contentStream.showText("Net total: "+invoice.getNetTotal()+" PLN");
            contentStream.endText();

            contentStream.close();

            document.save(filePath);
            System.out.println("Invoice saved: "+filePath);

        } catch (IOException e) {
            System.err.println("Unable to save invoice");
        }
    }
}
