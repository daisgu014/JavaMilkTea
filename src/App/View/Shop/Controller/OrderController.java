package App.View.Shop.Controller;

import App.View.Shop.ShopGUI;
import App.View.Shop.model.OrderDetailsModel;
import Entity.Customer;
import Entity.OrderDetail;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static App.View.Shop.loadData.customers;
import static App.View.Shop.loadData.orderDetails;

public class OrderController {
    private ShopGUI  Obs;

    public OrderController(ShopGUI shopGUI){
        this.Obs=shopGUI;
    }

    public void setObs(ShopGUI obs) {
        Obs = obs;
    }
    public ShopGUI getObs() {
        return Obs;
    }

    public OrderController(){
    }
    public boolean checkPreProduct(OrderDetail orderDetail){
        return  orderDetail.getQuantity()<=orderDetail.getProduct().getQty(orderDetail.getSize());
    }

    public boolean checkProductChoose(JPanel jPanel,OrderDetail orderDetail){
        boolean exited =false;
        if(checkPreProduct(orderDetail)){
            for(OrderDetail o : orderDetails){
                if(o.getProduct()==orderDetail.getProduct()){
                    if(o.getSize().equalsIgnoreCase(orderDetail.getSize())){
                        if(o.getQuantity()+orderDetail.getQuantity()<o.getProduct().getQty(o.getSize())){
                            o.setQuantity(o.getQuantity()+orderDetail.getQuantity());
                            exited=true;
                        }else {
                            JOptionPane.showMessageDialog(jPanel,orderDetail.getProduct().getProductName()+" không đủ số lượng");
                        }

                    }
                }
            }
            if(!exited){
                orderDetails.add(orderDetail);
            }
        }else{
            JOptionPane.showMessageDialog(jPanel,orderDetail.getProduct().getProductName()+" không đủ số lượng");
        }
        return exited;
    }
    public void AddOrderDetails(JPanel jPanel,OrderDetail orderDetail){
            checkProductChoose(jPanel,orderDetail);
            System.out.println("Danh sách đơn hàng");
            orderDetails.forEach(orderDetail1 -> {
                System.out.println(orderDetail1.getProduct().getProductName()+"--"+orderDetail1.getQuantity());
            });

    }
    public ArrayList<OrderDetailsModel> getOrderDetailsModels(ArrayList<OrderDetail> orderDetails){
        ArrayList<OrderDetailsModel> orderDetailsModels = new ArrayList<>();
        for(OrderDetail o : orderDetails){
            orderDetailsModels.add(new OrderDetailsModel(o));
        }
        return orderDetailsModels;
    }
    public void EditEvent(OrderDetail orderDetail, String size, Integer qty) {
    if(!orderDetail.getSize().equalsIgnoreCase(size)){
        for(OrderDetail o : orderDetails){
            if(o.getProduct()==orderDetail.getProduct()){
                if(o.getSize().equalsIgnoreCase(size)){
                    if((o.getQuantity()+qty)<=orderDetail.getProduct().getQty(orderDetail.getSize())){
                        o.setQuantity(o.getQuantity()+qty);
                        orderDetails.remove(orderDetail);
                    }else{
                        JOptionPane.showMessageDialog(null,"Số lượng sản phẩm "+orderDetail.getProduct().getProductName()+" " +orderDetail.getSize()+" không dủ");
                    }
                }else {
                 if(qty<=orderDetail.getProduct().getQty(size)){
                     orderDetail.setSize(size);
                     orderDetail.setQuantity(qty);
                 }else {
                     JOptionPane.showMessageDialog(null,"Số lượng sản phẩm "+orderDetail.getProduct().getProductName()+" " +orderDetail.getSize()+" không dủ");
                 }
                }
                break;
            }
        }
    }else if(orderDetail.getSize().equalsIgnoreCase(size)){
        for(OrderDetail o : orderDetails){
            if(o.getProduct()==orderDetail.getProduct()){
                if(orderDetail.getQuantity()!=qty){
                    if(qty<=orderDetail.getProduct().getQty(o.getSize())){
                        orderDetail.setQuantity(qty);
                    }else {
                        JOptionPane.showMessageDialog(null,"Số lượng sản phẩm "+orderDetail.getProduct().getProductName()+" " +orderDetail.getSize()+" không dủ");
                    }
                }
            }
        }
    }
    }

}
