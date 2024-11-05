package com.website.system.invoice;

import com.website.system.product.datamodel.Product;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class InvoicePdfSaver {

    public void saveInvoiceToPdf(Invoice invoice, String filePath) {
        List<Product> invoiceProducts = invoice.getProducts();
        try(PDDocument document = new PDDocument()){
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(100, 700);

            contentStream.showText("Invoice No: "+invoice.getInvoiceNumber());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Issue Date: "+invoice.getIssueDate());
            contentStream.newLineAtOffset(0,-15);
            contentStream.showText("Client: "+invoice.getClient().getFirstName() +" "+invoice.getClient().getLastName());
            contentStream.newLineAtOffset(0,-15);
            contentStream.showText("Products:");
            contentStream.newLineAtOffset(0,-15);

            for (int i = 0; i < invoiceProducts.size(); i++) {
                contentStream.showText(i+1 +". " + invoiceProducts.get(i).getName() + " " + invoiceProducts.get(i).getPrice()+"PLN");
                contentStream.newLineAtOffset(0,-15);
            }

            contentStream.newLineAtOffset(0,-30);
            BigDecimal grossTotal = BigDecimal.valueOf(invoice.getGrossTotal()).setScale(2, RoundingMode.HALF_UP);
            contentStream.showText("Total: "+grossTotal+" PLN");
            contentStream.endText();

            contentStream.close();

            document.save(filePath);
            System.out.println("Invoice saved: "+filePath);

        } catch (IOException e) {
            System.err.println("Unable to save invoice");
        }
    }
}
