package App.Model;

import App.View.Shop.loadData;
import Entity.Import;
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
                    i.getProductName(),
                    i.getSize(),
                    i.getQuantity()
            });
        }
        return objects;
    }

    public ArrayList<String> getProductNames() {
        loadData.management.setProductManagement();
        return loadData.management.getProductManagement().getProductNameList();
    }

    public ArrayList<String> getSizesByProductName(String name) {
        loadData.management.setProductManagement();
        return loadData.management.getProductManagement().findByName(name).getSizeStrings();
    }

    public void addPreparations(String productName, String size, int qty) {
        logic.addPreparations(productName, size, qty);
    }

    public void updateStorage() {
        logic.updateStorage();
    }

    public boolean removePreparation(Import i) {
        return logic.removePreparation(i);
    }

    public Import getImportAt(int index) {
        return logic.getImportAt(index);
    }
}
