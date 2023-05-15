package App.View.CrudGUI;

import App.Controller.CheckInput;
import App.Controller.ProductController;
import App.Model.ProductTable;
import App.View.Shop.Controller.OrderController;
import App.View.Shop.ShopGUI;
import App.View.Shop.loadData;
import Entity.Employee;
import Entity.Product;
import Entity.Size;
import Entity.WorkPosition;
import Logic.Management;
import Logic.ProductManagement;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class ProductGUI extends CrudGUI{
    private ArrayList<ProductTable> productTableArrayList;
    private ProductController productController;
    private int index;
    Management management = new Management();
    OrderController orderController = new OrderController();

    public OrderController getOrderController() {
        return orderController;
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

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
        RoundButton add = new RoundButton("Add",Color.decode("#1CA7EC"),Color.decode("#9AD9EA"));
        RoundButton edit = new RoundButton("Edit",Color.decode("#1CA7EC"),Color.decode("#9AD9EA"));
        RoundButton delete = new RoundButton("Delete",Color.decode("#F44336"),Color.decode("#F88279"));
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
                        try {
//                            String s = valueOf(filepath.getAbsolutePath());
//                            String[] words = s.split("////");
//                            words = words[words.length - 1].split("/");
//                            s = words[words.length - 1];
                            ImageIcon imageIcon = new ImageIcon(filepath.getAbsolutePath()); // replace with the actual path to your image file
                            ImageIcon scaledIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT));
                            productFormAdd.getLbImange().setIcon(scaledIcon);
                            productFormAdd.getLbImange().setText(filepath.getAbsolutePath());
//                            productFormAdd.getLbImange().setText("src/Assets/Images/"+s);
                        }catch (Exception exception){

                        }
                    }
                });
                Object[] message = {productFormAdd};
                RoundButton btnAccept = new RoundButton("Accept",Color.decode("#1CA7EC"),Color.decode("#9AD9EA"));
                btnAccept.setPreferredSize(new Dimension(100, 30));
                RoundButton btnCancel = new RoundButton("Cancel",Color.decode("#7C8594"),Color.decode("#DDDEE5"));
                btnCancel.setPreferredSize(new Dimension(100, 30));
                btnAccept.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
//                        CheckInput checkInput = new CheckInput();
                        boolean checkActive = false;
                        for (ProductTable productTable : productTableArrayList){
                            if ( productTable.getProductName().toLowerCase().trim().equals(productFormAdd.getTfProductName().getText().toLowerCase())){
                                checkActive = true;
                            }
                        }
                        if (productFormAdd.getTfProductName().getText().trim().equals("")==false){
                            if (checkActive==false){
                                Product product = new Product(
                                        0,
                                        productFormAdd.getTfProductName().getText(),
                                        productFormAdd.getCbCategory().getSelectedIndex()+1,
                                        productFormAdd.getLbImange().getText(),
                                        null,
                                        null
                                );
                                product = productController.InsertProduct(product);
                                ProductTable productTable = new ProductTable(
                                        product.getProductId(),
                                        product.getProductName(),
                                        String.valueOf(productFormAdd.getCbCategory().getSelectedItem()),
                                        product.getImagePath(),
                                        product.getCreateAt(),
                                        product.getDeleteAt()
                                );
                                productTableArrayList.add(productTable);
                                DefaultTableModel model = (DefaultTableModel) getTable().getModel();
                                model.addRow(new Object[]{
                                        productTable.getProductId(),
                                        productTable.getProductName(),
                                        productTable.getCategory(),
                                        productTable.getCreateAt(),
                                        productTable.getDeleteAt()
                                });

                                System.out.println(productTable.getImagePath());
                                JOptionPane.getRootFrame().dispose();
                                loadData.products.add(product);
                            }else {
                                JOptionPane.showMessageDialog(null, "Sản phẩm đã tồn tại !",
                                        "Create Product", JOptionPane.INFORMATION_MESSAGE,
                                        new ImageIcon("src/Assets/Icons/warning.png")
                                        );
                            }
                        }else {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin !",
                                    "Create Product", JOptionPane.INFORMATION_MESSAGE,
                                    new ImageIcon("src/Assets/Icons/warning.png")
                                    );
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
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index != -1){
                    ProductFormUpdate productFormUpdate = new ProductFormUpdate();
                    productFormUpdate.getTfProductName().setText(String.valueOf(getTable().getValueAt(index,1)));
                    productFormUpdate.getCbCategory().setSelectedItem(getTable().getValueAt(index,2));
                    ImageIcon imageIcon = new ImageIcon(String.valueOf(productTableArrayList.get(index).getImagePath())); // replace with the actual path to your image file
                    ImageIcon scaledIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT));
                    productFormUpdate.getLbImange().setIcon(scaledIcon);
                    productFormUpdate.getLbImange().setText(null);
                    productFormUpdate.getBtnUpload().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFileChooser fileChooser = new JFileChooser();
                            fileChooser.setDialogTitle("Upload Image");
                            fileChooser.showOpenDialog(null);
                            File filepath = fileChooser.getSelectedFile();
                            try {
                                String s = valueOf(filepath.getAbsolutePath());
                                ImageIcon imageIcon = new ImageIcon(s); // replace with the actual path to your image file
                                ImageIcon scaledIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT));
                                productFormUpdate.getLbImange().setIcon(scaledIcon);
//                                productFormUpdate.getLbImange().setText(null);
                            }catch (Exception exception){
                                System.out.println(exception);
                            }
                        }
                    });
                    Object[] message = {productFormUpdate};
                    RoundButton btnAccept = new RoundButton("Accept",Color.decode("#1CA7EC"),Color.decode("#9AD9EA"));
                    btnAccept.setPreferredSize(new Dimension(100, 30));
                    RoundButton btnCancel = new RoundButton("Cancel",Color.decode("#7C8594"),Color.decode("#DDDEE5"));
                    btnCancel.setPreferredSize(new Dimension(100, 30));
                    btnAccept.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Product product = new Product(
                                    Integer.parseInt(String.valueOf(getTable().getValueAt(index,0))),
                                    productFormUpdate.getTfProductName().getText(),
                                    productFormUpdate.getCbCategory().getSelectedIndex()+1,
                                    productFormUpdate.getLbImange().getText(),
                                    null,
                                    null
                            );
                            productController.UpdateProduct(product);

                            System.out.println(loadData.products.size());
                            getTable().setValueAt(product.getProductName(),index,1);
                            getTable().setValueAt(productFormUpdate.getCbCategory().getSelectedItem(),index,2);
                        }
                    });

                    btnCancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.getRootFrame().dispose(); // Close the dialog
                        }
                    });
                    Object[] options = {btnAccept,btnCancel};
                    int check = JOptionPane.showOptionDialog(null, message, "Update Product",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(""),
                            options, options[0]);
                }else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn Sản phẩm !",
                            "Update Product", JOptionPane.INFORMATION_MESSAGE,
                            new ImageIcon("src/Assets/Icons/chat.png")
                            );
                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index != -1){
                    int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?",
                            "Delete Product",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.ERROR_MESSAGE,
                            new ImageIcon("src/Assets/Icons/warning.png")
                    );
                    if (choice == JOptionPane.YES_OPTION) {
                        Product product = new Product(
                                Integer.parseInt(String.valueOf(getTable().getValueAt(index,0))),
                                null
                        );
                        productController.DeleteProduct(product);
//                        System.out.println(product.getProductId());
                        getTable().setValueAt(java.time.LocalDate.now(),index,4);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn Sản phẩm !",
                            "Update Product", JOptionPane.INFORMATION_MESSAGE,
                            new ImageIcon("src/Assets/Icons/chat.png")
                    );
                }

            }
        });

        setBtnAdd(add);
        setBtnUpdate(edit);
        setBtnDelete(delete);
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
