package App.View.ScreenGUI;

import App.Model.GUI;
import App.View.CustomerGUI;
import App.View.Shop.ShopGUI;
import App.View.StatisticView.StatisticView;

import java.util.ArrayList;

public class ManagementGUI {
    public ArrayList<GUI> GUIs = new ArrayList<>();

    public ManagementGUI() {
        GUI saleGUI = new GUI("Sale", new ShopGUI());
        GUI customerGUI = new GUI("Customer", new CustomerGUI());
        GUI statisticGUI = new GUI("Statistic", new StatisticView());
        GUIs.add(saleGUI);
        GUIs.add(customerGUI);
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
