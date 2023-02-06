import App.Model.Category;
import DAL.CategoryDAO;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Category> categories = new ArrayList<>();
        categories = CategoryDAO.retrieve();
        for (Category category : categories) {
            System.out.println(category.getCategoryName());
        }
    }
}