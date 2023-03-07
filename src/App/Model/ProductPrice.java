package App.Model;

public class ProductPrice {
    private Product product;
    private float productPrice;
    private String productSize;

    public ProductPrice() {
    }

    public ProductPrice(Product product, float productPrice, String productSize) {
        this.product = product;
        this.productPrice = productPrice;
        this.productSize = productSize;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }
}
