import App.Model.Category;
import DAL.CategoryDAO;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Category> categories = new ArrayList<>();
        CategoryDAO categoryDAO = new CategoryDAO();
        categories = categoryDAO.getAll();
        for (Category category : categories) {
            System.out.println(category.getCategoryName()+category.getCreateAt().toString());
        }

    }
}