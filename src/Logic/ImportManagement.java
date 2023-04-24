package Logic;

import App.View.Shop.loadData;
import DAL.ImportDAO;
import Entity.Import;

import java.util.ArrayList;

public class ImportManagement {
    private ImportDAO importAccess;
    private ArrayList<Import> preparations;

    public ImportManagement() {
        importAccess = new ImportDAO();
        preparations = new ArrayList<>();
    }

    public ArrayList<Import> getPreparations() {
        return preparations;
    }


    public void addPreparations(String productName, String size, int qty) {
        boolean existed = false;
        for (Import i : preparations) {
            if (i.getProductName().equalsIgnoreCase(productName) && i.getSize().equalsIgnoreCase(size)) {
                i.setQuantity(i.getQuantity() + qty);
                existed = true;
            }
        }
        if(!existed) {
            preparations.add(new Import(productName, size, qty));
        }
    }

    public void addPreparations(Import i) {
    }

    public Import getImportAt(int index) {
        return preparations.get(index);
    }

    public void updateStorage() {
        importAccess.updateStorageProduct(preparations);
        preparations = new ArrayList<>();
    }

    public boolean removePreparation(Import i) {
        return preparations.remove(i);
    }
}
