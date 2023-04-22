package App.Model;

import App.View.Shop.loadData;
import Entity.Import;
import Entity.Product;
import Logic.ImportManagement;

import java.util.ArrayList;

public class ImportModel {
    private ImportManagement logic;

    public ImportModel() {
        logic = new ImportManagement();
    }

    public ImportManagement getLogic() {
        return logic;
    }

    public ArrayList<Object[]> getDataTable() {
        ArrayList<Object[]> objects = new ArrayList<>();
        int index = 1;
        for(Import i : logic.getPreparations()) {
            objects.add(new Object[] {
                    String.valueOf(index++),
                    i.getProduct().getProductName(),
                    i.getSize(),
                    i.getQuantity()
            });
        }
        return objects;
    }

    public ArrayList<String> getProductNames() {
        return loadData.management.getProductManagement().getProductNameList();
    }

    public ArrayList<String> getSizesByProductName(String name) {
        return loadData.management.getProductManagement().findByName(name).getSizeStrings();
    }
}