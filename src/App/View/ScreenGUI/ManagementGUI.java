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
        GUI saleGUI = new GUI("SaleGUI", new ShopGUI());
        GUI customerGUI = new GUI("CustomerGUI", new CustomerGUI());
        GUI StaticView = new GUI("StaticView", new StatisticView());
        GUIs.add(saleGUI);
        GUIs.add(customerGUI);
        GUIs.add(StaticView);
    }

    public ArrayList<GUI> getGUIs() {
        return GUIs;
    }

    public void setGUIs(ArrayList<GUI> GUIs) {
        this.GUIs = GUIs;
    }
    public ArrayList<GUI> getGUISale(){
        return GUIs;
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
