package App.Model;

import java.sql.Date;

public class Product {
    private int productId;
    private String productName;
    private Integer category;
    private String imagePath;
    private Date createAt;
    private Date deleteAt;

    public Product() {
    }

    public Product(int productId, String productName, Integer category, String imagePath, Date createAt, Date deleteAt) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.imagePath = imagePath;
        this.createAt = createAt;
        this.deleteAt = deleteAt;
    }

    public Product(String productName, Integer category, String imagePath, Date createAt) {
        this.productName = productName;
        this.category = category;
        this.imagePath = imagePath;
        this.createAt = createAt;
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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }
}
