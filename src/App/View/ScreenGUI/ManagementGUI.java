package App.View.ScreenGUI;

import App.Model.GUI;
import App.View.CrudGUI.*;
import App.View.CustomerGUI;
import App.View.ImportProductView;
import App.View.OrderGUI;
import App.View.Shop.ShopGUI;
import App.View.StatisticView.StatisticView;

import java.util.ArrayList;

public class ManagementGUI {
    public ArrayList<GUI> GUIs = new ArrayList<>();

    public ManagementGUI() {
        ShopGUI shopGUI = new ShopGUI();
        ProductGUI productScreen = new ProductGUI();
        productScreen.getOrderController().setObs(shopGUI);
        GUI saleGUI = new GUI("Sale", shopGUI, GUI.icons.get("Sale"));
        GUI customerGUI = new GUI("Customer", new CustomerGUI(), GUI.icons.get("Customer"));
        GUI productGUI = new GUI("Product", productScreen, GUI.icons.get("Product"));
        GUI categoryGUI = new GUI("Category", new CategoryGUI(), GUI.icons.get("Category"));
        GUI sizeGUI = new GUI("Size", new SizeGUI(), GUI.icons.get("Size"));
        GUI employeeGUI = new GUI("Employee", new EmployeeGUI(), GUI.icons.get("Employee"));
        GUI accountGUI = new GUI("Account", new AccountGUI(), GUI.icons.get("Account"));
        GUI positionGUI = new GUI("Work Position", new WorkPositionGUI(), GUI.icons.get("Position"));
        GUI importGUI = new GUI("Import", new ImportProductView(), GUI.icons.get("Import"));
        GUI statisticGUI = new GUI("Statistic", new StatisticView(), GUI.icons.get("Statistic"));
        GUI orderGUI = new GUI("Order", new OrderGUI(), GUI.icons.get("Order"));
        GUI productSizeGUI = new GUI("Product & Size", new ProductSizeGUI(), GUI.icons.get("ProductSize"));

        GUIs.add(saleGUI);
        GUIs.add(customerGUI);
        GUIs.add(categoryGUI);
        GUIs.add(productGUI);
        GUIs.add(sizeGUI);
        GUIs.add(employeeGUI);
        GUIs.add(accountGUI);
        GUIs.add(positionGUI);
        GUIs.add(importGUI);
        GUIs.add(statisticGUI);
        GUIs.add(orderGUI);
        GUIs.add(productSizeGUI);
    }

    public ArrayList<GUI> getGUIs() {
        return GUIs;
    }

    public void setGUIs(ArrayList<GUI> GUIs) {
        this.GUIs = GUIs;
    }
    public GUI findByName (String name){
        GUI newGUI = new GUI();
        for(GUI gui : GUIs){
            if(gui.getGUIName().equalsIgnoreCase(name.trim())){
                newGUI=gui;
            }
        }
        return newGUI;
    }
}
