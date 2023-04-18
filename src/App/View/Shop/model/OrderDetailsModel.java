package App.View.Shop.model;

import Entity.OrderDetail;

public class OrderDetailsModel {
    private String Product;
    private Integer qty;
    private Integer totalPrice;
    private OrderDetail orderDetail;

    public OrderDetailsModel(OrderDetail orderDetailsModel){
        this.Product="Product Name: "+orderDetailsModel.getProduct()+"\nSize: "+
                orderDetailsModel.getSize()+"\nUnit Price: "+orderDetailsModel.getProduct().getPrice(orderDetail.getSize());
        this.qty=orderDetailsModel.getQuantity();
        this.totalPrice=orderDetailsModel.getQuantity()*orderDetailsModel.getProduct().getPrice(orderDetail.getSize());
        this.orderDetail=orderDetailsModel;
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
