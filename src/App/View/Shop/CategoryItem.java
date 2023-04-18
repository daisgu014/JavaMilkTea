package App.View.Shop;

import Entity.Product;
import Logic.Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CategoryItem extends JPanel {
    Management management = new Management();
    ShopGUI shopGUI = new ShopGUI();
    private Label categoryLabel;
    public CategoryItem(String categoryName){
        categoryLabel = new Label(categoryName);
        categoryLabel.setPreferredSize(new Dimension(200,100));
        add(categoryLabel,BorderLayout.CENTER);
        setPreferredSize(new Dimension(200,100));
        setBackground(Color.BLUE);
        categoryLabel.setAlignment(0);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<Product> products = new ArrayList<>();
                for(Product o : loadData.products){
                    if(management.getCategoryManagement().findById(o.getCategory()).getCategoryName().equalsIgnoreCase(categoryName)){
                        products.add(o);
                        shopGUI.render(products);
                    }
                }

            }
        });
    }

}
