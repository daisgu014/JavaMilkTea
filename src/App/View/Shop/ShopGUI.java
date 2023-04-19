package App.View.Shop;

import Entity.Category;
import Entity.Product;
import Logic.Management;
import Logic.ProductManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ShopGUI extends JPanel {
    private JTextField  txtSearch;
    private JButton btnSearch;
    private JPanel searchBox;
    private JPanel searchPanel;
    private JTable cartTable;
    private JPanel productListPanel;
    private DefaultTableModel cartTableModel;
    private JScrollPane scrollPane;
    Management management = new Management();
    public ShopGUI(){
        setLayout(new BorderLayout());
        productListPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        productListPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        render(loadData.products);
        scrollPane = new JScrollPane(productListPanel);
        scrollPane.setPreferredSize(new Dimension(1080, 800));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane,BorderLayout.CENTER);
        JPanel categoryPanel = new JPanel(new GridLayout(0,1));
        categoryPanel.setPreferredSize(new Dimension(300, 800));
        categoryPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        loadData.categories.forEach(cate->
        {
            ArrayList<Product> products = new ArrayList<>();

            String category =cate.getCategoryName();
            JButton jButton = new JButton(category);
            categoryPanel.add(jButton);
            jButton.addActionListener(event->{
                products.clear();
                for(Product o : loadData.products){
                    if(management.getCategoryManagement().findById(o.getCategory()).getCategoryName().equalsIgnoreCase(category)){
                        products.add(o);
                    }
                }
                System.out.println(products.size());
                render(products);
                scrollPane.validate();
            });
        });
        JPanel orderTable = new JPanel();
        cartTableModel = new DefaultTableModel();
        cartTableModel.addColumn("STT");
        cartTableModel.addColumn("Tên sản phẩm");
        cartTableModel.addColumn("Số lượng");
        cartTableModel.addColumn("Giá");
        cartTable=new JTable(cartTableModel);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        orderTable.add(cartScrollPane,BorderLayout.CENTER);
        orderTable.setBackground(Color.ORANGE);
        add(categoryPanel,BorderLayout.WEST);
        add(orderTable,BorderLayout.EAST);
        searchPanel = new JPanel();
        searchBox = new JPanel();
        btnSearch= new JButton("Search");
        txtSearch = new JTextField(40);
        txtSearch.setPreferredSize(new Dimension(400,50));
        btnSearch.setPreferredSize(new Dimension(100,50));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("Arial", Font.BOLD, 18));
        searchBox.setFont(new Font("Arial", Font.PLAIN, 18));
        btnSearch.setBackground(new Color(250, 152, 58));
        searchBox.add(txtSearch,BorderLayout.CENTER);
        searchBox.add(btnSearch,BorderLayout.CENTER);
        searchPanel.add(searchBox,BorderLayout.CENTER);
        searchBox.setPreferredSize(new Dimension(600,60));
        searchBox.setBackground(new Color(250, 211, 144));
        searchPanel.setPreferredSize(new Dimension(700,70));
        add(searchPanel,BorderLayout.NORTH);
        searchProduct();
    }
    public void searchProduct()
    {
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Product> products = new ArrayList<>();
                products.clear();
                if(!txtSearch.getText().equals("")){
                    String pattern = ".*" + txtSearch.getText() + ".*";
                   for(Product product : loadData.products){
                     if(product.getProductName().toLowerCase().matches(pattern.toLowerCase())){
                         products.add(product);
                     }
                    }
                    if(products.size()>0){
                        render(products);
                        scrollPane.validate();
                    }else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm!!!!");
                        txtSearch.setText("");
                        productListPanel.removeAll();
                        render(loadData.products);
                        scrollPane.validate();
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Nhập nội dung tìm kiếm!!!!");
                }
            }
        });
    }

    public void render(ArrayList<Product> products){
        //  productListPanel.invalidate();
        System.out.println("000"+products.size());
        if(productListPanel!=null){
            productListPanel.removeAll();
        }
        products.forEach(e->{
            productListPanel.add(new Items(e));
        });
//        productListPanel.repaint();
//        productListPanel.paintImmediately(productListPanel.getVisibleRect());
//        productListPanel.validate();

    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("JAVA COFFEE");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(new ShopGUI());
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}