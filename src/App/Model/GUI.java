package App.Model;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;

public class GUI {
    private String GUIName;
    private JPanel GUI;
    private ImageIcon icon;
    public static HashMap<String, String> icons = new HashMap<>(){{
        put("Sale", "src/Assets/Icons/pos.png");
        put("Customer", "src/Assets/Icons/costumer.png");
        put("Product", "src/Assets/Icons/pos.png");
        put("Employee", "src/Assets/Icons/resource.png");
        put("Account", "src/Assets/Icons/pos.png");
        put("Import", "src/Assets/Icons/import.png");
        put("Statistic", "src/Assets/Icons/pie-chart.png");
        put("Order", "src/Assets/Icons/order-now.png");
    }};
    public GUI(String GUIName, JPanel GUI) {
        this.GUIName = GUIName;
        this.GUI = GUI;
    }

    public GUI(String GUIName, JPanel GUI, String path) {
        this.GUIName = GUIName;
        this.GUI = GUI;
        try {
            ImageIcon imageIcon = new ImageIcon(new File(path).toURI().toURL());
            Image image = imageIcon.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT);
            this.icon = new ImageIcon(image);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public GUI(){

    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    public void setIcon(String path) {
        try {
            ImageIcon imageIcon = new ImageIcon(new File(path).toURI().toURL());
            Image image = imageIcon.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT);
            this.icon = new ImageIcon(image);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getGUIName() {
        return GUIName;
    }

    public void setGUIName(String GUIName) {
        this.GUIName = GUIName;
    }

    public JPanel getGUI() {
        return GUI;
    }

    public void setGUI(JPanel GUI) {
        this.GUI = GUI;
    }
}
