package App.Model;

import java.sql.Date;

public class Topping {
    private int toppingId;
    private String toppingName;
    private float sellPrice;
    private float amount;
    private int unitOnePart;
    private float importPrice;
    private Date createAt;
    private Date deleteAt;

    public Topping() {
    }

    public Topping(int toppingId, String toppingName, float sellPrice, float amount,
                   int unitOnePart, float importPrice, Date createAt, Date deleteAt) {
        this.toppingId = toppingId;
        this.toppingName = toppingName;
        this.sellPrice = sellPrice;
        this.amount = amount;
        this.unitOnePart = unitOnePart;
        this.importPrice = importPrice;
        this.createAt = createAt;
        this.deleteAt = deleteAt;
    }

    public Topping(String toppingName, float sellPrice, float amount, int unitOnePart, float importPrice, Date createAt) {
        this.toppingName = toppingName;
        this.sellPrice = sellPrice;
        this.amount = amount;
        this.unitOnePart = unitOnePart;
        this.importPrice = importPrice;
        this.createAt = createAt;
    }

    public int getToppingId() {
        return toppingId;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getUnitOnePart() {
        return unitOnePart;
    }

    public void setUnitOnePart(int unitOnePart) {
        this.unitOnePart = unitOnePart;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
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
