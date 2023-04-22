package Logic;

import App.View.Shop.loadData;
import Entity.Import;
import Entity.Product;

import java.util.ArrayList;

public class ImportManagement {
    private ArrayList<Import> preparations;

    public ImportManagement() {
        preparations = new ArrayList<>();
    }

    public ArrayList<Import> getPreparations() {
        return preparations;
    }


    public void addPreparations(String productName, String size, int qty) {
        Product p = loadData.management.getProductManagement().findByName(productName);
        for (Import i : preparations) {
            if (i.getProduct().getProductName().equalsIgnoreCase(productName)) {
                i.setQuantity(i.getQuantity() + qty);
            }
        }
        preparations.add(new Import(p, size, qty));
    }

    public void addPreparations(Import i) {
    }
}
