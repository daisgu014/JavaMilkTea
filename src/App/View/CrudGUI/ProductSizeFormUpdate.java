package App.View.CrudGUI;

import App.Controller.SizeController;
import Entity.Size;
import org.jfree.chart.block.CenterArrangement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProductSizeFormUpdate extends FormDialog{
    private JTextField tfProductId;
    private JTextField tfProductName;
    private JTextField tfPrice;
    private JTextField tfStorage;
    private JLabel lbSize;

    public ProductSizeFormUpdate(JTextField tfProductId, JTextField tfProductName, JTextField tfPrice, JTextField tfStorage, JLabel cbSize) {
        this.tfProductId = tfProductId;
        this.tfProductName = tfProductName;
        this.tfPrice = tfPrice;
        this.tfStorage = tfStorage;
        this.lbSize = cbSize;
    }
    public ProductSizeFormUpdate(){
        SceneForm();
    }

    public JTextField getTfProductId() {
        return tfProductId;
    }

    public void setTfProductId(JTextField tfProductId) {
        this.tfProductId = tfProductId;
    }

    public JTextField getTfProductName() {
        return tfProductName;
    }

    public void setTfProductName(JTextField tfProductName) {
        this.tfProductName = tfProductName;
    }

    public JTextField getTfPrice() {
        return tfPrice;
    }

    public void setTfPrice(JTextField tfPrice) {
        this.tfPrice = tfPrice;
    }

    public JTextField getTfStorage() {
        return tfStorage;
    }

    public void setTfStorage(JTextField tfStorage) {
        this.tfStorage = tfStorage;
    }

    public JLabel getLbSize() {
        return lbSize;
    }

    public void setLbSize(JLabel cbSize) {
        this.lbSize = cbSize;
    }
    @Override
    public JPanel pnContainer(){
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(490,300));
        pn.setLayout(new FlowLayout(FlowLayout.CENTER));
        pn.setBorder(BorderFactory.createLineBorder(Color.red));
        JLabel lbId = new JLabel("Product ID: ",SwingConstants.CENTER);
        setTfProductId(new JTextField());
        pn.add(pnRows(lbId,getTfProductId()));
        JLabel lbName = new JLabel("Product Name: ",SwingConstants.CENTER);
        setTfProductName(new JTextField());
        pn.add(pnRows(lbName,getTfProductName()));
        setLbSize(new JLabel());
        getLbSize().setPreferredSize(new Dimension(400,30));
        pn.add(getLbSize());
        JLabel lbPrice = new JLabel("Price: ",SwingConstants.CENTER);
        setTfPrice(new JTextField());
        pn.add(pnRows(lbPrice,getTfPrice()));
        JLabel lbStorage = new JLabel("Storage: ",SwingConstants.CENTER);
        setTfStorage(new JTextField());
        pn.add(pnRows(lbStorage,getTfStorage()));
        return pn;
    }
    @Override
    public void SceneForm(){
        setPreferredSize(new Dimension(500,600));
        setLayout(new FlowLayout());
        setLbTitle( new JLabel("Update Product Size",SwingConstants.CENTER));
        add(pnTitle());
        add(pnContainer());
        setVisible(true);
    }
}
