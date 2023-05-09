package App.Controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PrintPDF {
    public Image getImage(JPanel jPanel){
        BufferedImage image = new BufferedImage(jPanel.getWidth(),
                jPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        jPanel.paint(image.getGraphics());
        try {
            File output = new File("src/image.png");
            ImageIO.write(image, "png", output);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return image;

    }
    public void printToPDF(java.awt.Image awtImage, String fileName) {
        try {
            Document d = new Document();
            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(
                    fileName));
            d.open();
            com.itextpdf.text.Image iTextImage = com.itextpdf.text.Image.getInstance(writer, awtImage, 1);
            iTextImage.scalePercent(70);
            d.add(iTextImage);

            d.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
