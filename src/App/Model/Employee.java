package App.Model;

public class Employee {
    private int employeeId;
    private String employeeName;
    private String employeePhone;
    private WorkPosition workPosition;
    private WorkType workType;
    private Account account;
    private DailyWork[] dailyWorks;

    public Employee() {
    }

    public Employee(int employeeId, String employeeName, String employeePhone,
                    WorkPosition workPosition, WorkType workType, Account account, DailyWork[] dailyWorks) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.workPosition = workPosition;
        this.workType = workType;
        this.account = account;
        this.dailyWorks = dailyWorks;
    }

    public Employee(String employeeName, String employeePhone, WorkPosition workPosition, WorkType workType) {
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.workPosition = workPosition;
        this.workType = workType;
    }

    public int getEmployeeId() {
        return employeeId;
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

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
