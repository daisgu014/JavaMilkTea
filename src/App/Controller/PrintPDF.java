package App.Controller;

import App.View.CustomerGUI;
import App.View.Shop.ShopGUI;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

public class PrintPDF {
    public Image getImage(JPanel jPanel){
        BufferedImage image = new BufferedImage(794,
                1123, BufferedImage.TYPE_INT_RGB);
        jPanel.paint(image.getGraphics());
        return image;

    }
    public void printToPDF(java.awt.Image awtImage, String fileName) {
        try {
            Document d = new Document();
            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(
                    fileName));
            d.open();
            com.itextpdf.text.Image iTextImage = com.itextpdf.text.Image.getInstance(writer, awtImage, 1);
            iTextImage.setAbsolutePosition(50, 50);
            iTextImage.scalePercent(100);            d.add(iTextImage);

            d.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        CustomerGUI customerGUI = new CustomerGUI();
        frame.add(customerGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        PrintPDF printPDF = new PrintPDF();
        Image image = printPDF.getImage(customerGUI);
        //printPDF.printToPDF(image, "src/CustomerGUI.pdf");
    }
}
