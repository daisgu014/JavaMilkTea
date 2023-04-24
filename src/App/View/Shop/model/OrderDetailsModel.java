package App.View.Shop.model;

import Entity.OrderDetail;

public class OrderDetailsModel {
    private String Product;
    private String size;
    private Integer qty;
    private Integer totalPrice;
    private OrderDetail orderDetail;

    public OrderDetailsModel(OrderDetail orderDetailsModel){
        this.Product=orderDetailsModel.getProduct().getProductName();
        this.size=orderDetailsModel.getSize();
        this.qty=orderDetailsModel.getQuantity();
        this.totalPrice=this.qty*orderDetailsModel.getProduct().getPrice(this.size);
        this.orderDetail=orderDetailsModel;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}
