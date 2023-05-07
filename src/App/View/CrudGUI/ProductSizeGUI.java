package App.View.CrudGUI;

import App.Controller.CheckInput;
import App.Controller.ProductController;
import App.Model.ProductTable;
import Entity.Product;
import Entity.ProductSize;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class ProductSizeGUI extends CrudGUI{
    private ArrayList<Product> productArrayList;
    private ProductController productController;
    private int index;

    public ProductSizeGUI(){
        productController = new ProductController();
        getDataProduct();
        setProductScene();
        Scene();
    }
    public void getDataProduct(){
        productArrayList = productController.getProductAll();
    }
    public void setProductScene(){
        setTitle("Product Size");
        setDataTable();
        setButton();
    }
    public void setDataTable(){
        JTable table = productController.setDataTableSize();
        setTable(table);
    }
    public void setButton(){
        JButton add = new JButton("Add") ;
        JButton edit = new JButton("Edit") ;
        JButton delete = new JButton("Delete");
//        JButton exit = new JButton("Exit");
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
                if (index != -1){
                    ProductSizeFormAdd productSizeFormAdd = new ProductSizeFormAdd();
                    productSizeFormAdd.getTfProductId().setText(String.valueOf(getTable().getValueAt(index,0)));
                    productSizeFormAdd.getTfProductId().setEditable(false);
                    productSizeFormAdd.getTfProductName().setText(String.valueOf(getTable().getValueAt(index,1)));
                    productSizeFormAdd.getTfProductName().setEditable(false);
                    Object[] message = {productSizeFormAdd};
                    JButton btnAccept = new JButton("Add");
                    JButton btnCancel = new JButton("Cancel");
                    btnAccept.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            CheckInput checkInput = new CheckInput();
                            ArrayList<ProductSize> productSizeArrayList = new ArrayList<>();
                            if (checkInput.checkNumber(productSizeFormAdd.getTfPrice().getText())==true
                                    && checkInput.checkNumber(productSizeFormAdd.getTfStorage().getText())==true ){
                                int id = Integer.parseInt(productSizeFormAdd.getTfProductId().getText());
                                String size = String.valueOf(productSizeFormAdd.getCbSize().getSelectedItem());
                                if (checkInput.checkActiveProdSize(id,size,productArrayList)==false){
                                    ProductSize productSize = new ProductSize(
                                            String.valueOf(productSizeFormAdd.getCbSize().getSelectedItem()),
                                            Integer.parseInt(productSizeFormAdd.getTfPrice().getText()),
                                            Integer.parseInt(productSizeFormAdd.getTfStorage().getText())
                                    );
                                    productSizeArrayList.add(productSize);
                                    productController.InsertProductSize(
                                            Integer.parseInt(productSizeFormAdd.getTfProductId().getText()),productSize
                                    );
                                    Product product = new Product(
                                            Integer.parseInt(productSizeFormAdd.getTfProductId().getText()),
                                            productSizeFormAdd.getTfProductName().getText(),
                                            null,
                                            null,
                                            null,
                                            null,
                                            productSizeArrayList
                                    );
                                    productArrayList.add(product);
                                    if(getTable().getValueAt(index,2)==null){
                                        getTable().setValueAt(productSizeFormAdd.getCbSize().getSelectedItem(),index,2);
                                        getTable().setValueAt(productSizeFormAdd.getTfPrice().getText(),index,3);
                                        getTable().setValueAt(productSizeFormAdd.getTfStorage().getText(),index,4);
                                    }else {
                                        DefaultTableModel model = (DefaultTableModel) getTable().getModel();
                                        model.addRow(new Object[]{
                                                productSizeFormAdd.getTfProductId().getText(),
                                                productSizeFormAdd.getTfProductName().getText(),
                                                productSizeFormAdd.getCbSize().getSelectedItem(),
                                                productSizeFormAdd.getTfPrice().getText(),
                                                productSizeFormAdd.getTfStorage().getText()
                                        });
                                    }
                                    JOptionPane.getRootFrame().dispose(); // Close the dialog
                                }else{
                                    JOptionPane.showMessageDialog(null, "Sản phẩm đã có Size: "+size+" !",
                                            "Create Product Size", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }else {
                                JOptionPane.showMessageDialog(null, "Vui lòng nhập số !",
                                        "Create Product Size", JOptionPane.INFORMATION_MESSAGE);
                            }
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
                }else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn Sản phẩm !",
                            "Update Product Size", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (index != -1){
                    ProductSizeFormUpdate productSizeFormUpdate = new ProductSizeFormUpdate();
                    productSizeFormUpdate.getTfProductId().setText(String.valueOf(getTable().getValueAt(index,0)));
                    productSizeFormUpdate.getTfProductId().setEditable(false);
                    productSizeFormUpdate.getTfProductName().setText(String.valueOf(getTable().getValueAt(index,1)));
                    productSizeFormUpdate.getTfProductName().setEditable(false);
                    productSizeFormUpdate.getLbSize().setText("Size:" + getTable().getValueAt(index,2));
                    productSizeFormUpdate.getLbSize().setHorizontalAlignment(SwingConstants.CENTER);
                    productSizeFormUpdate.getTfPrice().setText(String.valueOf(getTable().getValueAt(index,3)));
                    productSizeFormUpdate.getTfStorage().setText(String.valueOf(getTable().getValueAt(index,4)));
                    Object[] message = {productSizeFormUpdate};
                    JButton btnAccept = new JButton("Update");
                    JButton btnCancel = new JButton("Cancel");
                    btnAccept.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArrayList<ProductSize> productSizeArrayList = new ArrayList<>();
                            CheckInput checkInput = new CheckInput();
                            if (checkInput.checkNumber(productSizeFormUpdate.getTfPrice().getText())==true
                                    && checkInput.checkNumber(productSizeFormUpdate.getTfStorage().getText())==true){
                                ProductSize productSize = new ProductSize(
                                        String.valueOf(productSizeFormUpdate.getLbSize().getText()),
                                        Integer.parseInt(productSizeFormUpdate.getTfPrice().getText()),
                                        Integer.parseInt(productSizeFormUpdate.getTfPrice().getText())
                                );
                                productSizeArrayList.add(productSize);
                                productController.UpdateProductSize(
                                        Integer.parseInt(productSizeFormUpdate.getTfProductId().getText()),productSize
                                );
                                Product product = new Product(
                                        Integer.parseInt(productSizeFormUpdate.getTfProductId().getText()),
                                        productSizeFormUpdate.getTfProductName().getText(),
                                        null,
                                        null,
                                        null,
                                        null,
                                        productSizeArrayList
                                );
                                productArrayList.add(product);
                                getTable().setValueAt(productSizeFormUpdate.getTfPrice().getText(),index,3);
                                getTable().setValueAt(productSizeFormUpdate.getTfStorage().getText(),index,4);
                                JOptionPane.getRootFrame().dispose(); // Close the dialog
                            }else {
                                JOptionPane.showMessageDialog(null, "Vui lòng nhập số !",
                                        "Update Product Size", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    });

                    btnCancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.getRootFrame().dispose(); // Close the dialog
                        }
                    });
                    Object[] options = {btnAccept,btnCancel};
                    int check = JOptionPane.showOptionDialog(null, message, "Update Product Size",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(""),
                            options, options[0]);
                }else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn Sản phẩm !",
                            "Update Product Size", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductSizeFormUpdate productSizeFormUpdate = new ProductSizeFormUpdate();
                if(index != -1){

                }else{

                }
            }
        });

        setBtnAdd(add);
        setBtnUpdate(edit);
        setBtnDelete(delete);
        getBtnDelete().setVisible(false);
    }
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(1280,800);
        ProductSizeGUI productGUI = new ProductSizeGUI();
        jFrame.add(productGUI);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}