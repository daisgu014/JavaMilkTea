package App.Model;

public class ProductTopping {
    private Product product;
    private Topping topping;

    public ProductTopping() {
    }

    public ProductTopping(Product product, Topping topping) {
        this.product = product;
        this.topping = topping;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }
}
