package Entity;

public class ProductSize {
    private String size;
    private Integer productPrice;
    private int storage;

    public ProductSize() {
    }

    public ProductSize(String size, Integer productPrice, int storage) {
        this.size = size;
        this.productPrice = productPrice;
        this.storage = storage;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }
}
