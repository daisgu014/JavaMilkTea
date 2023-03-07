package App.Model;

public class Ingredient {
    private int ingredientId;
    private String ingredientName;
    private String ingredientType;
    private int storage;
    private String supplier;
    private int price;

    public Ingredient() {
    }

    public Ingredient(int ingredientId, String ingredientName, String ingredientType,
                      int storage, String supplier, int price) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
        this.storage = storage;
        this.supplier = supplier;
        this.price = price;
    }

    public Ingredient(String ingredientName, String ingredientType, int storage, String producer, int price) {
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
        this.storage = storage;
        this.supplier = producer;
        this.price = price;
    }

    public int getIngredientId() {
        return ingredientId;
    }


    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
