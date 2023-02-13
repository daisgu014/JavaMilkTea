package App.Model;

public class IncomeDetail {
    private Ingredient ingredient;
    private int quantity;
    private int receivedQty;

    public IncomeDetail() {
    }

    public IncomeDetail(Ingredient ingredient, int quantity, int receivedQty) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.receivedQty = receivedQty;
    }

    public IncomeDetail(Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReceivedQty() {
        return receivedQty;
    }

    public void setReceivedQty(int receivedQty) {
        this.receivedQty = receivedQty;
    }
}
