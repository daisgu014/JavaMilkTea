package App.View.ScreenGUI;

import App.Model.GUI;
import App.View.CustomerGUI;
import App.View.Shop.ShopGUI;
import App.View.StatisticView.StatisticView;

import java.util.ArrayList;
import java.util.HashMap;

public class ManagementGUI {
    public ArrayList<GUI> GUIs = new ArrayList<>();

    public ManagementGUI() {
        GUI saleGUI = new GUI("Sale", new ShopGUI(), GUI.icons.get("Sale"));
        GUI customerGUI = new GUI("Customer", new CustomerGUI(), GUI.icons.get("Customer"));
        GUI productGUI = new GUI("Product", new StatisticView(), GUI.icons.get("Statistic"));
        GUI employeeGUI = new GUI("Employee", new StatisticView(), GUI.icons.get("Employee"));
        GUI accountGUI = new GUI("Account", new StatisticView(), GUI.icons.get("Account"));
        GUI importGUI = new GUI("Import", new StatisticView(), GUI.icons.get("Import"));
        GUI statisticGUI = new GUI("Statistic", new StatisticView(), GUI.icons.get("Statistic"));
        GUIs.add(saleGUI);
        GUIs.add(customerGUI);
        GUIs.add(productGUI);
        GUIs.add(employeeGUI);
        GUIs.add(accountGUI);
        GUIs.add(importGUI);
        GUIs.add(statisticGUI);
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
