package App.Model;

public class ProductRecipe {
    private Product product;
    private Ingredient ingredient;
    private float productQty;
    private float ingredientQty;

    public ProductRecipe() {
    }

    public ProductRecipe(Product product, Ingredient ingredient, float productQty, float ingredientQty) {
        this.product = product;
        this.ingredient = ingredient;
        this.productQty = productQty;
        this.ingredientQty = ingredientQty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public float getProductQty() {
        return productQty;
    }

    public void setProductQty(float productQty) {
        this.productQty = productQty;
    }

    public float getIngredientQty() {
        return ingredientQty;
    }

    public void setIngredientQty(float ingredientQty) {
        this.ingredientQty = ingredientQty;
    }
}
