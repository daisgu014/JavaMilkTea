package App.View.CrudGUI;

import App.Controller.AccountController;
import App.Controller.CheckInput;
import App.Controller.SizeController;
import Entity.Account;
import Entity.Employee;
import Entity.Size;
import Entity.WorkPosition;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class SizeGUI extends CrudGUI{
    private ArrayList<Size> sizeArrayList;
    private SizeController sizeController ;
    private int index;

    public SizeGUI(JButton btnAdd, JButton btnUpdate, JButton btnDelete, JButton btnExit, JTable table, String title){
        super(btnAdd,btnUpdate,btnDelete,btnExit,table,title);
    }
    public SizeGUI(){
        sizeController = new SizeController();
        getSizeList();
        setSceneSize();
        Scene();
    }
    public void getSizeList(){
        sizeArrayList = sizeController.getData();
    }
    public void setDataTable() {
        JTable table = sizeController.getDataTable();
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
                index = selectedRow;
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SizeFormAdd sizeFormAdd = new SizeFormAdd();
                Object[] message = {sizeFormAdd};
                JButton btnAccept = new JButton("Add");
                JButton btnCancel = new JButton("Cancel");
                btnAccept.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (sizeFormAdd.getTfSign().getText().trim().equals("")==false && sizeFormAdd.getTfDescription().getText().trim().equals("")==false){
                            Size size = new Size(sizeFormAdd.getTfSign().getText(),sizeFormAdd.getTfDescription().getText());
                            sizeController.InsertSize(size);
                            sizeArrayList.add(size);
                            DefaultTableModel model = (DefaultTableModel) getTable().getModel();
                            model.addRow(new Object[]{size.getSign(),size.getDescription()});

                        }else {
                            JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin!",
                                    "Create Size", JOptionPane.INFORMATION_MESSAGE);
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
                int check = JOptionPane.showOptionDialog(null, message, "Create Size",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(""),
                        options, options[0]);
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index!=-1){
                    SizeFormUpdate sizeFormUpdate = new SizeFormUpdate();
                    Object[] message = {sizeFormUpdate};
                    sizeFormUpdate.getTfSign().setText(String.valueOf(getTable().getValueAt(index,0)));
                    sizeFormUpdate.getTfSign().setEditable(false);
                    sizeFormUpdate.getTfDescription().setText(String.valueOf(getTable().getValueAt(index,1)));

                    JButton btnAccept = new JButton("Update");
                    JButton btnCancel = new JButton("Cancel");
                    btnAccept.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(sizeFormUpdate.getTfDescription().getText().trim().equals("")==false){
                                Size size = new Size(sizeFormUpdate.getTfSign().getText(),sizeFormUpdate.getTfDescription().getText());
                                sizeController.UpdateSize(size);
                                getTable().setValueAt(size.getDescription(),index,1);
                                JOptionPane.getRootFrame().dispose();
                            }else {
                                JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin!",
                                        "Create Size", JOptionPane.INFORMATION_MESSAGE);
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
                    int check = JOptionPane.showOptionDialog(null, message, "Update Size",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(""),
                            options, options[0]);
                }else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn Size!",
                            "Create Size", JOptionPane.INFORMATION_MESSAGE);
                }

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
    public void setSceneSize(){
        setTitle("Size");
        setDataTable();
        setButton();
    }
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(1280,800);
        SizeGUI accountGUI = new SizeGUI();
        jFrame.add(accountGUI);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
