package DAL;

import java.util.ArrayList;

public abstract class DAO<T> {
    Database database = new Database();
    public abstract ArrayList<T> getAll();
    public abstract T get(int id);

    public abstract void create (T t);

    public abstract void update (T t);
    public abstract void delete (T t);

    public abstract void deleteById (int id);
    

}
