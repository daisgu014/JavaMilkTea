package App.Model;

public class Employee {
    private int employeeId;
    private String employeeName;
    private String employeePhone;
    private WorkPosition workPosition;
    private Account account;

    public Employee() {
    }

    public Employee(int employeeId, String employeeName, String employeePhone, WorkPosition workPosition) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.workPosition = workPosition;
    }
    public Employee(int employeeId, String employeeName, String employeePhone, WorkPosition workPosition, Account account) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.workPosition = workPosition;
        this.account =account;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public WorkPosition getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(WorkPosition workPosition) {
        this.workPosition = workPosition;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
