package DAL;


import App.Model.Ingredient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class IngredientDAO extends DAO<Ingredient> {

    @Override
    public ArrayList<Ingredient> getAll() {
        Database db = new Database();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        Statement stmt = db.getStmt();
        try {
            ResultSet rs = stmt.executeQuery("Select * from Ingredients");
            while(rs != null && rs.next()) {
                ingredients.add(new Ingredient(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6)
                        ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ingredients;
    }

    @Override
    public Ingredient get(int id) {
        Database db = new Database();
        PreparedStatement preStmt = db.getPreStmt("select * from Ingredients where = ?");
        try {
            preStmt.setInt(1, id);
            ResultSet rs = preStmt.executeQuery();
            while(rs!=null && rs.next()) {
                return new Ingredient(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int create(Ingredient ingredient) {
        Database db = new Database();
        PreparedStatement preStmt = db.getPreStmt("insert into Ingredients(" +
                "ingredientName, ingredientType, storage, Producer, price) values (?, ?, ?, ?, ?) returning id");
        try {
            preStmt.setString(1, ingredient.getIngredientName());
            preStmt.setString(2, ingredient.getIngredientType());
            preStmt.setInt(3, ingredient.getStorage());
            preStmt.setString(4, ingredient.getSupplier());
            preStmt.setInt(5, ingredient.getPrice());

            ResultSet rs = preStmt.executeQuery();
            while(rs!=null && rs.next()) {
                return  rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public void update(Ingredient ingredient) {

    }

    @Override
    public void delete(Ingredient ingredient) {

    }

    @Override
    public void deleteById(int id) {

    }
}
