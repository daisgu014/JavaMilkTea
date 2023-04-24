package App.View.CrudGUI;

import App.Controller.CheckInput;
import App.Controller.ProductController;
import Entity.Employee;
import Entity.Product;
import Entity.WorkPosition;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProductGUI extends CrudGUI{
    private ArrayList<Product> productArrayList;
    private ProductController productController;
    private int index;

    public ProductGUI(){
        productController = new ProductController();
        getDataProduct();
        setProductScene();
        Scene();
    }
    public void getDataProduct(){
        productArrayList = productController.getProductArrayList();
    }
    public void setProductScene(){
        setTitle("Product");
        setDataTable();
        setButton();
    }
    public void setDataTable(){
        JTable table = productController.setDataTable();
        setTable(table);
    }
    public void setButton(){
        JButton add = new JButton("Add") ;
        JButton edit = new JButton("Edit") ;
        JButton delete = new JButton("Delete");
        JButton exit = new JButton("Exit");
        index = -1;
        getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = getTable().getSelectedRow();
//                String selectedValue = String.valueOf(getTable().getValueAt(selectedRow, 0));
//                index = Integer.parseInt(selectedValue);
                index = selectedRow;
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        setBtnAdd(add);
        setBtnUpdate(edit);
        setBtnDelete(delete);
        setBtnExit(exit);
    }
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(1280,800);
        ProductGUI productGUI = new ProductGUI();
        jFrame.add(productGUI);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
