package App.View.CrudGUI;

import App.Controller.EmployeeController;
import App.Controller.SizeController;
import Entity.Employee;
import Entity.Size;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeGUI extends CrudGUI{
    private ArrayList<Employee> employeeArrayList;
    private EmployeeController employeeController;
    private int index ;
    public EmployeeGUI(JButton btnAdd, JButton btnUpdate, JButton btnDelete, JButton btnExit, JTable table, String title){
        super(btnAdd,btnUpdate,btnDelete,btnExit,table,title);
    }
    public EmployeeGUI(){
        employeeController = new EmployeeController();
        getList();
        setSceneSize();
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
        JButton add = new JButton("Add") ;
        JButton edit = new JButton("Edit") ;
        JButton delete = new JButton("Delete");
        JButton exit = new JButton("Exit");
        getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = getTable().getSelectedRow();
                String selectedValue = String.valueOf(getTable().getValueAt(selectedRow, 0));
                index = Integer.parseInt(selectedValue);
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index != 0){
                    for (int i = 0 ; i< employeeArrayList.size();i++){
                        if (employeeArrayList.get(i).getEmployeeId() == index){
                            System.out.println(employeeArrayList.get(i).getEmployeeName());
                        }
                    }
                }else {
                    System.out.println("Click");
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
