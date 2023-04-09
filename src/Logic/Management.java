package Logic;

public class Management {
    private AccountManagement accountManagement;
    private CategoryManagement categoryManagement;
    private CustomerManagement customerManagement;
    private EmployeeManagement employeeManagement;
    private OrderManagement orderManagement;
    private ProductManagement productManagement;
    private SizeManagement sizeManagement;
    private WorkPositionManagement workPositionManagement;

    public Management() {
        accountManagement = new AccountManagement();
        categoryManagement = new CategoryManagement();
        customerManagement = new CustomerManagement();
        employeeManagement = new EmployeeManagement();
        orderManagement = new OrderManagement();
        productManagement = new ProductManagement();
        sizeManagement = new SizeManagement();
        workPositionManagement = new WorkPositionManagement();
    }

    public AccountManagement getAccountManagement() {
        return accountManagement;
    }

    public CategoryManagement getCategoryManagement() {
        return categoryManagement;
    }

    public CustomerManagement getCustomerManagement() {
        return customerManagement;
    }

    public EmployeeManagement getEmployeeManagement() {
        return employeeManagement;
    }

    public OrderManagement getOrderManagement() {
        return orderManagement;
    }

    public ProductManagement getProductManagement() {
        return productManagement;
    }

    public SizeManagement getSizeManagement() {
        return sizeManagement;
    }

    public WorkPositionManagement getWorkPositionManagement() {
        return workPositionManagement;
    }
}
