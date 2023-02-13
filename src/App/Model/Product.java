package App.Model;

public class Product {
    private int productId;
    private String productName;
    private Category category;
    private String imagePath;

    public Product() {
    }

    public Product(int productId, String productName, Category category, String imagePath) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.imagePath = imagePath;
    }

    public Product(String productName, Category category, String imagePath) {
        this.productName = productName;
        this.category = category;
        this.imagePath = imagePath;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
