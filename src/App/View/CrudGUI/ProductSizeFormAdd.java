package App.View.CrudGUI;

import App.Controller.SizeController;
import App.Model.EmployeeModel;
import Entity.Size;
import Entity.WorkPosition;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProductSizeFormAdd extends FormDialog{
    private JTextField tfProductId;
    private JTextField tfProductName;
    private JTextField tfPrice;
    private JTextField tfStorage;
    private JComboBox cbSize;

    public ProductSizeFormAdd(JTextField tfProductId, JTextField tfProductName, JTextField tfPrice, JTextField tfStorage, JComboBox cbSize) {
        this.tfProductId = tfProductId;
        this.tfProductName = tfProductName;
        this.tfPrice = tfPrice;
        this.tfStorage = tfStorage;
        this.cbSize = cbSize;
    }
    public ProductSizeFormAdd(){
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

    public JComboBox getCbSize() {
        return cbSize;
    }

    public void setCbSize(JComboBox cbSize) {
        this.cbSize = cbSize;
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
        ArrayList<Size> sizeArrayList = new ArrayList<>();
        SizeController sizeController = new SizeController();
        sizeArrayList = sizeController.getData();
        String[] Sizes = new String[sizeArrayList.size()];
        for (int i = 0;i < sizeArrayList.size();i++ ){
            Sizes[i] = sizeArrayList.get(i).getSign();
        }
//        cbWorkPosition = new JComboBox(WorkPosition);
        setCbSize(new JComboBox(Sizes));
        getCbSize().setPreferredSize(new Dimension(200,30));
        pn.add(getCbSize());
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
        setLbTitle( new JLabel("Add Product Size",SwingConstants.CENTER));
        add(pnTitle());
        add(pnContainer());
        setVisible(true);
    }
}
