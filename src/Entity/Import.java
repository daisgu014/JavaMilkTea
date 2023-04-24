package Entity;

public class Import {
    private String productName;
    private String size;
    private int quantity;

    public Import() {
    }

    public Import(String productName, String size, int quantity) {
        this.productName = productName;
        this.size = size;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
