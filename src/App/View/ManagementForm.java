package App.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ManagementForm extends JPanel implements ActionListener {
    private JLabel header, title;
    private JTable dataTable;
    private JButton addBtn, updateBtn, deleteBtn;
    private JPanel leftForm;

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public ManagementForm(String info) throws HeadlessException {

        this.header = new JLabel(info);
        this.title = new JLabel("Details Information");
        this.addBtn = new JButton("Add");
        this.updateBtn = new JButton("Update");
        this.deleteBtn = new JButton("Delete");
        this.dataTable = new JTable();

        this.leftForm = new JPanel();
        leftForm.setLayout(new BoxLayout(leftForm, BoxLayout.Y_AXIS));

        JPanel btnGrPnl = new JPanel(new GridLayout(1, 3, 16, 0));
        btnGrPnl.add(addBtn);
        btnGrPnl.add(updateBtn);
        btnGrPnl.add(deleteBtn);
        leftForm.add(btnGrPnl);


        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(header, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(dataTable, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(leftForm, gbc);


    }

    public void initGUI() {

    }


    public JLabel getHeader() {
        return header;
    }

    public void setHeader(JLabel header) {
        this.header = header;
    }

    public String getTitle() {
        return title.getText();
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public JTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(JTable dataTable) {
        this.dataTable = dataTable;
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public void setAddBtn(JButton addBtn) {
        this.addBtn = addBtn;
    }

    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    public void setDeleteBtn(JButton deleteBtn) {
        this.deleteBtn = deleteBtn;
    }

    public JButton getUpdateBtn() {
        return updateBtn;
    }

    public void setUpdateBtn(JButton updateBtn) {
        this.updateBtn = updateBtn;
    }
}
