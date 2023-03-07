package DAL;

import java.util.ArrayList;

public abstract class DAO<T> {
    Database database = new Database();
    public abstract ArrayList<T> getAll();
    public abstract T get(int id);

    /**
     * Take an objetct t and create a new row with data of t in database.
     * @param t object is added DB
     * @return id of entity in database, if not return -1
     */

    public abstract int create (T t);

    public abstract void update (T t);
    public abstract void delete (T t);

    public abstract void deleteById (int id);
    

}
