package App.View.CrudGUI;

import App.Controller.AccountController;
import App.Controller.CheckInput;
import App.Controller.EmployeeController;
import App.Controller.SizeController;
import Entity.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeGUI extends CrudGUI{
    private ArrayList<Employee> employeeArrayList;
    private EmployeeController employeeController;
    private int index ;
    public EmployeeGUI(JButton btnAdd, JButton btnUpdate, JButton btnDelete, JTable table, String title){
        super(btnAdd,btnUpdate,btnDelete,table,title);
    }
    public EmployeeGUI(){
        employeeController = new EmployeeController();
        getList();
        setSceneEmpoyee();
        Scene();
    }
    public void getList(){
        employeeArrayList = employeeController.getData();
    }
    public void setDataTable() {
        JTable table = employeeController.getDataTable();
        setTable(table);
    }
    public void setButton(){
        RoundButton add = new RoundButton("Add", Color.decode("#1CA7EC"),Color.decode("#9AD9EA"));
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

                EmployeeFormAdd employeeForm = new EmployeeFormAdd();
                Object[] message = {employeeForm};
                RoundButton btnAccept = new RoundButton("Accept",Color.decode("#1CA7EC"),Color.decode("#9AD9EA"));
                btnAccept.setPreferredSize(new Dimension(100, 30));
                RoundButton btnCancel = new RoundButton("Cancel",Color.decode("#7C8594"),Color.decode("#DDDEE5"));
                btnCancel.setPreferredSize(new Dimension(100, 30));
                btnAccept.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CheckInput checkInput = new CheckInput();
                        if (employeeForm.getTfName().getText().trim().equals("")==false){
                            if(checkInput.checkPhone(employeeForm.getTfPhone().getText())==true){
                                WorkPosition workPosition = new WorkPosition(
                                        employeeForm.getCbWorkPosition().getSelectedIndex()+1,
                                        null,
                                        0);
                                Employee employee = new Employee(
                                        0,
                                        employeeForm.getTfName().getText(),
                                        employeeForm.getTfPhone().getText(),
                                        workPosition
                                );
                                Employee newEmployee = new Employee();
                                newEmployee = employeeController.InsertEmployee(employee);
                                employeeArrayList.add(newEmployee);
                                DefaultTableModel model = (DefaultTableModel) getTable().getModel();
                                model.addRow(new Object[]{
                                        newEmployee.getEmployeeId(),
                                        newEmployee.getEmployeeName(),
                                        newEmployee.getEmployeePhone(),
                                        newEmployee.getWorkPosition().getName()
                                });
                                JOptionPane.getRootFrame().dispose();
                            }else {
                                JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ",
                                        "Update Employee", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }else {
                            JOptionPane.showMessageDialog(null, "Tên nhân viên không được bỏ trống",
                                    "Update Employee", JOptionPane.INFORMATION_MESSAGE);
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
                int check = JOptionPane.showOptionDialog(null, message, "Create Employee",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(""),
                        options, options[0]);
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeFormUpdate employeeForm = new EmployeeFormUpdate();
                if (index >= 0){
                    employeeForm.getTfId().setText(String.valueOf(employeeArrayList.get(index).getEmployeeId()));
                    employeeForm.getTfId().setEditable(false);
                    employeeForm.getTfName().setText(String.valueOf(employeeArrayList.get(index).getEmployeeName()));
                    employeeForm.getTfPhone().setText(String.valueOf(employeeArrayList.get(index).getEmployeePhone()));
                    employeeForm.getCbWorkPosition().setSelectedIndex(employeeArrayList.get(index).getWorkPosition().getPositionId()-1);
                    Object[] message = {employeeForm};
                    RoundButton btnAccept = new RoundButton("Accept",Color.decode("#1CA7EC"),Color.decode("#9AD9EA"));
                    btnAccept.setPreferredSize(new Dimension(100, 30));
                    RoundButton btnCancel = new RoundButton("Cancel",Color.decode("#7C8594"),Color.decode("#DDDEE5"));
                    btnCancel.setPreferredSize(new Dimension(100, 30));
                    btnAccept.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
//                        System.out.println(employeeForm.getTfName().getText());
//                        System.out.println(employeeForm.getCbWorkPosition().getSelectedItem());
                            CheckInput checkInput = new CheckInput<>();
                            boolean active = false;
                            for (Employee emp : employeeArrayList){
                                if (emp.getEmployeeId()== Integer.parseInt(employeeForm.getTfId().getText())){
                                    active = true;
                                }
                            }
                            if (active == true){
                                if (employeeForm.getTfName().getText().trim().equals("")==false){
                                    if (checkInput.checkPhone(employeeForm.getTfPhone().getText())==true){
                                        WorkPosition workPosition = new WorkPosition(
                                                employeeForm.getCbWorkPosition().getSelectedIndex()+1,
                                                null,
                                                0
                                        );
                                        Employee employee = new Employee(
                                                Integer.parseInt(employeeForm.getTfId().getText()),
                                                employeeForm.getTfName().getText(),
                                                employeeForm.getTfPhone().getText(),
                                                workPosition
                                        );
                                        employeeController.UpdateEmployee(employee);
                                        employeeArrayList.set(index,employee);
                                        getTable().setValueAt(employeeForm.getTfName().getText(),index,1);
                                        getTable().setValueAt(employeeForm.getTfPhone().getText(),index,2);
                                        getTable().setValueAt(employeeForm.getCbWorkPosition().getSelectedItem(),index,3);
                                        JOptionPane.getRootFrame().dispose();
                                    }else {
                                        JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ",
                                                "Update Employee", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }else {
                                    JOptionPane.showMessageDialog(null, "Tên nhân viên không được bỏ trống",
                                            "Update Employee", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }else {
                                JOptionPane.showMessageDialog(null, "Mã nhân viên không chính xác",
                                        "Update Employee", JOptionPane.INFORMATION_MESSAGE);
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
                    int check = JOptionPane.showOptionDialog(null, message, "Update Employee",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(""),
                            options, options[0]);
                }else{
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên !",
                            "Update Employee", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index != -1){
                    int choice = JOptionPane.showOptionDialog(null, "Bạn có chắc chắn xóa nhân viên không?", "Save changes?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (choice == JOptionPane.YES_OPTION) {
                        Employee employee = new Employee(
                               Integer.parseInt(String.valueOf(getTable().getValueAt(index,0))),
                                null,
                                null,
                                null
                        );
                        try {
                            employeeController.DeleteEmployee(employee);
                            ((DefaultTableModel)getTable().getModel()).removeRow(index);
                            JOptionPane.getRootFrame().dispose();
                        }catch (Exception exception){
                            System.out.println(exception);
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn Nhân Viên !",
                            "Update Product", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        setBtnAdd(add);
        setBtnUpdate(edit);
        setBtnDelete(delete);
    }
    public void setSceneEmpoyee(){
        setTitle("Employee");
        setDataTable();
        setButton();
    }
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(1280,800);
        EmployeeGUI accountGUI = new EmployeeGUI();
        jFrame.add(accountGUI);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
