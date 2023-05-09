package App.View.CrudGUI;

import App.Model.EmployeeModel;
import Entity.WorkPosition;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EmployeeFormUpdate extends FormDialog{
    private JTextField tfId;
    private JTextField tfName;
    private JTextField tfPhone;
    private JComboBox cbWorkPosition;

    public EmployeeFormUpdate(JTextField tfId, JTextField tfName, JTextField tfPhone, JComboBox cbWorkPosition) {
        this.tfId = tfId;
        this.tfName = tfName;
        this.tfPhone = tfPhone;
        this.cbWorkPosition = cbWorkPosition;
    }
    public EmployeeFormUpdate(){
        SceneForm();
    }

    public JTextField getTfId() {
        return tfId;
    }

    public void setTfId(JTextField tfId) {
        this.tfId = tfId;
    }

    public JTextField getTfName() {
        return tfName;
    }

    public void setTfName(JTextField tfName) {
        this.tfName = tfName;
    }

    public JTextField getTfPhone() {
        return tfPhone;
    }

    public void setTfPhone(JTextField tfPhone) {
        this.tfPhone = tfPhone;
    }

    public JComboBox getCbWorkPosition() {
        return cbWorkPosition;
    }

    public void setCbWorkPosition(JComboBox cbWorkPosition) {
        this.cbWorkPosition = cbWorkPosition;
    }

//    public JPanel pnRows(JLabel jLabel, JTextField textField){
//        JPanel pn = new JPanel();
//        pn.setPreferredSize(new Dimension(480,50));
//        jLabel.setPreferredSize(new Dimension(200,30));
//        textField.setPreferredSize(new Dimension(200,30));
//        pn.add(jLabel);
//        pn.add(textField);
//        return pn;
//    }
    @Override
    public JPanel pnContainer(){
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(490,300));
        pn.setLayout(new FlowLayout());
        pn.setBorder(new RoundedBorder(20));
        JLabel lbIDEmp = new JLabel("Employee ID: ",SwingConstants.CENTER);
        setTfId(new JTextField());
        pn.add(pnRows(lbIDEmp,getTfId()));
        JLabel lbName = new JLabel("Name: ",SwingConstants.CENTER);
        setTfName(new JTextField());
        pn.add(pnRows(lbName,getTfName()));
        JLabel lbPhone = new JLabel("Phone: ",SwingConstants.CENTER);
        setTfPhone(new JTextField());
        pn.add(pnRows(lbPhone,getTfPhone()));
        ArrayList<WorkPosition> workPositionArrayList = new ArrayList<>();
        EmployeeModel employeeModel = new EmployeeModel();
        workPositionArrayList = employeeModel.getDataWorkPosition();
        String[] WorkPosition = new String[workPositionArrayList.size()];
        for (int i = 0;i < workPositionArrayList.size();i++ ){
            WorkPosition[i] = workPositionArrayList.get(i).getName();
        }
//        cbWorkPosition = new JComboBox(WorkPosition);
        setCbWorkPosition(new JComboBox(WorkPosition));
        getCbWorkPosition().setPreferredSize(new Dimension(200,30));
        pn.add(cbWorkPosition);
        return pn;
    }
    @Override
    public void SceneForm(){
        setPreferredSize(new Dimension(500,600));
        setLayout(new FlowLayout());
        setLbTitle( new JLabel("Employee",SwingConstants.CENTER));
        add(pnTitle());
        add(pnContainer());
        setVisible(true);
    }
    public static void main(String[] args) {
        EmployeeForm employeeForm = new EmployeeForm();
        Object[] message = {employeeForm};
        int check = JOptionPane.showConfirmDialog(null,message,"Update",JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE,
                new ImageIcon(""));
        if (check == JOptionPane.OK_OPTION){
            System.out.println(8);
        }
    }
}
