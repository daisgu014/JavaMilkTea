package Entity;

public class ProductSize {
    private String size;
    private float productPrice;
    private int storage;

    public ProductSize() {
    }

    public ProductSize(String size, float productPrice, int storage) {
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

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }
}
