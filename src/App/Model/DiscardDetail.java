package App.Model;

import java.sql.Date;

public class DiscardDetail {
    private Product discardProduct;
    private int quantity;
    private Date discardDate;

    public DiscardDetail() {
    }

    public DiscardDetail(Product discardProduct, int quantity, Date discardDate) {
        this.discardProduct = discardProduct;
        this.quantity = quantity;
        this.discardDate = discardDate;
    }

    public Product getDiscardProduct() {
        return discardProduct;
    }

    public void setDiscardProduct(Product discardProduct) {
        this.discardProduct = discardProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDiscardDate() {
        return discardDate;
    }

    public void setDiscardDate(Date discardDate) {
        this.discardDate = discardDate;
    }
}
