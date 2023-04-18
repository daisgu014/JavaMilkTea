package Main;

import App.View.ScreenGUI.MainScreen;
import Entity.Category;
import DAL.CategoryDAO;
import Logic.Management;
import java.util.ArrayList;

public class Main {
    public static Management management;

    public static void main(String[] args) {
        management = new Management();
//        ArrayList<Category> categories = new ArrayList<>();
//        CategoryDAO categoryDAO = new CategoryDAO();
//        categories = categoryDAO.getAll();
//        for (Category category : categories) {
//            System.out.println(category.getCategoryName()+category.getCreateAt().toString());
//        }
        new MainScreen();
    }
}