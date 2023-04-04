package App.Model;

public class ProductSize {
    private Product product;
    private Size size;
    private float productPrice;
    private int storage;

    public ProductSize() {
    }

    public ProductSize(Product product, Size size, float productPrice, int storage) {
        this.product = product;
        this.size = size;
        this.productPrice = productPrice;
        this.storage = storage;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
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
