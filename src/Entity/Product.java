package Entity;

import java.sql.Date;
import java.util.ArrayList;

public class Product {
    private int productId;
    private String productName;
    private Integer category;
    private String imagePath;
    private Date createAt;
    private Date deleteAt;
    private ArrayList<ProductSize> productSizes;
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
    public Product(int productId, String productName, Integer category, String imagePath, Date createAt, Date deleteAt, ArrayList<ProductSize> productSizes) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.imagePath = imagePath;
        this.createAt = createAt;
        this.deleteAt = deleteAt;
        this.productSizes=productSizes;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public ArrayList<ProductSize> getProductSizes() {
        return productSizes;
    }
    public ArrayList<String> getProductSizesString() {
        ArrayList<String> productSizeString = new ArrayList<>();
        productSizes.forEach(e->{
            productSizeString.add(e.getSize());
        });
        return productSizeString;
    }
    public Integer getPrice (String Size){
        Integer price=0;
        for(ProductSize o  : getProductSizes()){
            if(o.getSize().equalsIgnoreCase(Size)){
                price=o.getProductPrice();
            }
        }
        return  price;
    }
    public Integer getQty (String Size){
        Integer qty=0;
        for(ProductSize o  : getProductSizes()){
            if(o.getSize().equalsIgnoreCase(Size)){
                qty=o.getStorage();
            }
        }
        return  qty;
    }

    public void setProductSizes(ArrayList<ProductSize> productSizes) {
        this.productSizes = productSizes;
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
