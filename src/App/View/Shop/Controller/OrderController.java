package App.View.Shop.Controller;

import Entity.OrderDetail;

import javax.swing.*;

import static App.View.Shop.loadData.orderDetails;

public class OrderController {
    public void AddOrderDetails(JPanel jPanel,OrderDetail orderDetail){
        if(orderDetail.getQuantity()<=orderDetail.getProduct().getQty(orderDetail.getSize())){
            orderDetails.add(orderDetail);
            orderDetails.forEach(orderDetail1 -> {
                System.out.println(orderDetail1.getProduct().getProductName()+"--"+orderDetail1.getQuantity());
            });
        }else{
            JOptionPane.showMessageDialog(jPanel,"Số lượng sản phẩm không đủ");
        }
    }
}
