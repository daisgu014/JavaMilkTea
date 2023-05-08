package App.View.CrudGUI;

import App.Controller.CheckInput;
import App.Controller.ProductController;
import App.Model.ProductTable;
import Entity.Employee;
import Entity.Product;
import Entity.Size;
import Entity.WorkPosition;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class ProductGUI extends CrudGUI{
    private ArrayList<ProductTable> productTableArrayList;
    private ProductController productController;
    private int index;

    public ProductGUI(){
        productController = new ProductController();
        getDataProduct();
        setProductScene();
        Scene();
    }
    public void getDataProduct(){
        productTableArrayList = productController.getProductArrayList();
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
                ProductFormAdd productFormAdd = new ProductFormAdd();
                productFormAdd.getBtnUpload().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Upload Image");
                        fileChooser.showOpenDialog(null);
                        File filepath = fileChooser.getSelectedFile();
//            System.out.println(filepath.getAbsolutePath());
                        String s = valueOf(filepath.getAbsolutePath());
                        String[] words=s.split("\\\\");
                        words = words[words.length - 1].split("/");
                        s = words[words.length-1];
                        productFormAdd.getLbImange().setText(s);
                    }
                });
                Object[] message = {productFormAdd};
                JButton btnAccept = new JButton("Add");
                JButton btnCancel = new JButton("Cancel");
                btnAccept.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Product product = new Product(0,productFormAdd.getTfProductName().getText(),productFormAdd.getCbCategory().getSelectedIndex()+1,productFormAdd.getLbImange().getText(),null,null);
                        product = productController.InsertProduct(product);
                        ProductTable productTable = new ProductTable(product.getProductId(),product.getProductName(),String.valueOf(productFormAdd.getCbCategory().getSelectedItem()),product.getImagePath(),product.getCreateAt(),product.getDeleteAt());
                        productTableArrayList.add(productTable);
                        DefaultTableModel model = (DefaultTableModel) getTable().getModel();
                        model.addRow(new Object[]{productTable.getProductId(),productTable.getProductName(),productTable.getCategory(),productTable.getCreateAt(),productTable.getDeleteAt()});

                    }
                });

                btnCancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.getRootFrame().dispose(); // Close the dialog
                    }
                });
                Object[] options = {btnAccept,btnCancel};
                int check = JOptionPane.showOptionDialog(null, message, "Create Product",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(""),
                        options, options[0]);
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
