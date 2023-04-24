package App.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OrderGUI extends JPanel {
    private JTable orderTable;
    private DefaultTableModel orderTableModel;
    private JButton btnReceipt, btnPrintf;
    private JPanel header, main, footer;
    private JLabel label;
    public OrderGUI(){
        setPreferredSize(new Dimension(900, 800));
        label = new JLabel();
        label.setHorizontalAlignment(0);
        label.setText("Order Management");
        header = new JPanel();
        header.add(label,BorderLayout.CENTER);
        add(header,BorderLayout.SOUTH);
    }
}
