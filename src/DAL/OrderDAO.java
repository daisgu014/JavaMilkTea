package DAL;

import App.Model.Order;

import java.util.ArrayList;

public class OrderDAO extends DAO<Order>{
    Database database = new Database();
    @Override
    public ArrayList<Order> getAll() {
        return null;
    }

    @Override
    public Order get(int id) {
        return null;
    }

    @Override
    public int create(Order order) {
        return 0;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public void deleteById(int id) {

    }
}
