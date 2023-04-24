package App.View.CrudGUI;

import App.Controller.CheckInput;
import App.Controller.SizeController;
import App.Controller.WorkPositionController;
import Entity.Size;
import Entity.WorkPosition;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class WorkPositionGUI extends CrudGUI{
    private ArrayList<WorkPosition> workPositionArrayList;
    private WorkPositionController workPositionController;
    private int index;

    public WorkPositionGUI(JButton btnAdd, JButton btnUpdate, JButton btnDelete, JButton btnExit, JTable table, String title){
        super(btnAdd,btnUpdate,btnDelete,btnExit,table,title);
    }
    public WorkPositionGUI(){
        workPositionController = new WorkPositionController();
        getWorkPositionList();
        setSceneSize();
        Scene();
    }
    public void getWorkPositionList(){
        workPositionArrayList = workPositionController.getWorkPositions();
    }
    public void setDataTable() {
        JTable table = workPositionController.getDataTable();
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
                PositionFormAdd positionFormAdd = new PositionFormAdd();
                Object[] message = {positionFormAdd};
                JButton btnAccept = new JButton("Add");
                JButton btnCancel = new JButton("Cancel");
                btnAccept.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CheckInput checkInput = new CheckInput();
                        if (positionFormAdd.getTfName().getText().trim().equals("")==false || positionFormAdd.getTfLevel().getText().trim().equals("")==false){
                            if (checkInput.checkNumber(positionFormAdd.getTfLevel().getText().trim())==true){
                                WorkPosition workPosition = new WorkPosition(0,positionFormAdd.getTfName().getText(),Integer.parseInt(positionFormAdd.getTfLevel().getText()));
                                workPosition = workPositionController.InsertPosition(workPosition);
                                workPositionArrayList.add(workPosition);
                                DefaultTableModel model = (DefaultTableModel) getTable().getModel();
                                model.addRow(new Object[]{workPosition.getPositionId(),workPosition.getName(),workPosition.getPositionLvl()});
                            }else {
                                JOptionPane.showMessageDialog(null, "Level là số có 1 chữ số !",
                                        "Create Work Position", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }else {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin!",
                                    "Create Work Position", JOptionPane.INFORMATION_MESSAGE);
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
                int check = JOptionPane.showOptionDialog(null, message, "Create Work Position",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(""),
                        options, options[0]);
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index!=-1){
                    PositionFormUpdate positionFormUpdate = new PositionFormUpdate();
                    positionFormUpdate.getTfId().setText(String.valueOf(getTable().getValueAt(index,0)));
                    positionFormUpdate.getTfId().setEditable(false);
                    positionFormUpdate.getTfName().setText(String.valueOf(getTable().getValueAt(index,1)));
                    positionFormUpdate.getTfLevel().setText(String.valueOf(getTable().getValueAt(index,2)));
                    Object[] message = {positionFormUpdate};
                    JButton btnAccept = new JButton("Add");
                    JButton btnCancel = new JButton("Cancel");
                    btnAccept.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            CheckInput checkInput = new CheckInput();
                            if (positionFormUpdate.getTfName().getText().trim().equals("")==false || positionFormUpdate.getTfLevel().getText().trim().equals("")==false){
                                if (checkInput.checkNumber(positionFormUpdate.getTfLevel().getText().trim())==true){
                                    WorkPosition workPosition = new WorkPosition(Integer.parseInt(positionFormUpdate.getTfId().getText()),positionFormUpdate.getTfName().getText(),Integer.parseInt(positionFormUpdate.getTfLevel().getText()));
                                    workPositionController.UpdatePosition(workPosition);
//                                    workPositionArrayList.add(workPosition);
                                    getTable().setValueAt(workPosition.getName(),index,1);
                                    getTable().setValueAt(workPosition.getPositionLvl(),index,2);
                                }else {
                                    JOptionPane.showMessageDialog(null, "Level là số có 1 chữ số !",
                                            "Create Work Position", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }else {
                                JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin!",
                                        "Create Work Position", JOptionPane.INFORMATION_MESSAGE);
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
                    int check = JOptionPane.showOptionDialog(null, message, "Update Work Position",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(""),
                            options, options[0]);
            }else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn vị trí muốn chỉnh sửa!",
                        "Update Work Position", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
        delete.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Upload Image");
            fileChooser.showOpenDialog(null);
            File filepath = fileChooser.getSelectedFile();
//            System.out.println(filepath.getAbsolutePath());
            String s = valueOf(filepath.getAbsolutePath());
            String[] words=s.split("\\\\");
            s = words[words.length-1];
            System.out.println(s);
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
        setTitle("Work Position");
        setDataTable();
        setButton();
    }
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(1280,800);
        WorkPositionGUI accountGUI = new WorkPositionGUI();
        jFrame.add(accountGUI);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
