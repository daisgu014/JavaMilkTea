package App.Model;

import java.util.ArrayList;

public class OrderDetail {
    private Product product;
    private String productSize;
    private float price;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(Product product, String productSize, float price, int quantity) {
        this.product = product;
        this.productSize = productSize;
        this.price = price;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
