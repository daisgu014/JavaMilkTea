package App.View.Shop;

import App.View.Shop.model.OrderDetailsModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CartTable extends JPanel {
    private JTable cartJTable;
    private DefaultTableModel cartTableModel;
    public CartTable(ArrayList<OrderDetailsModel> orderDetailsModels){
        cartTableModel = new DefaultTableModel();
        cartTableModel.addColumn("STT");
        cartTableModel.addColumn("Tên sản phẩm");
        cartTableModel.addColumn("Số lượng");
        cartTableModel.addColumn("Giá");
        cartJTable=new JTable(cartTableModel);
        add(cartJTable, BorderLayout.CENTER);
    }
}
