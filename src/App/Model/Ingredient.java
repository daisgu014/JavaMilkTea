package App.Model;

import java.sql.Date;

public class Ingredient {
    private int ingredientId;
    private String ingredientName;
    private String ingredientType;
    private int storage;
    private int price;
    private Date createAt;
    private Date deleteAt;


    public Ingredient() {
    }

    public Ingredient(int ingredientId, String ingredientName,
                      String ingredientType, int storage, int price, Date createAt, Date deleteAt) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
        this.storage = storage;
        this.price = price;
        this.createAt = createAt;
        this.deleteAt = deleteAt;
    }

    public Ingredient(String ingredientName, String ingredientType, int storage, int price, Date createAt) {
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
        this.storage = storage;
        this.price = price;
        this.createAt = createAt;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }
}
