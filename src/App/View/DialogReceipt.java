package App.View;

import App.Controller.PrintPDF;
import App.View.Shop.loadData;
import App.View.Shop.productEditGUI;
import Entity.Order;
import Entity.OrderDetail;
import Logic.OrderManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterJob;
import java.util.Properties;

public class DialogReceipt extends JDialog {
    private JPanel Content, footer;
    private JButton btnPrint, btnClose;
    private Receipt receipt;

    public DialogReceipt(Order order){
        setSize(900,
                950);
        Content = new JPanel();
        receipt = new Receipt(order);
        Content.add(receipt);
        add(Content, BorderLayout.CENTER);
        btnPrint = new JButton("Print");
        btnClose = new JButton("Close");
        JPanel jPanelButton = new JPanel();
        jPanelButton.add(btnPrint);
        jPanelButton.add(btnClose);
        JPanel jPanel = new JPanel();
        footer = new JPanel(new GridLayout(1,0));
        footer.add(jPanel);
        footer.add(jPanelButton);
        footer.setPreferredSize(new Dimension(600,50));
        add(footer, BorderLayout.SOUTH);
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           PrintPDF printPDF = new PrintPDF();
                Image image = printPDF.getImage(receipt);
                String fileName = "src/"+order.getOrderDate()+order.getOrderId()+".pdf";
                printPDF.printToPDF(image,fileName);
                dispose();
//                java.awt.print.PrinterJob printerJob = PrinterJob.getPrinterJob();
//                printerJob.printDialog();
            }
        });
    }

    public static void main(String[] args) {
        OrderManagement orderManagement = new OrderManagement();
        JFrame jFrame = new JFrame();
        jFrame.setSize(500,500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DialogReceipt productEditGUI=new DialogReceipt(orderManagement.findById(4));
        productEditGUI.setVisible(true);
    }
}
