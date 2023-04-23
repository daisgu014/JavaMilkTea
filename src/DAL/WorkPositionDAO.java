package DAL;

import Entity.Employee;
import Entity.WorkPosition;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkPositionDAO extends DAO<WorkPosition> {
   Database dao = new Database();
    @Override
    public ArrayList<WorkPosition> getAll() {
        ArrayList<WorkPosition> workPositionArrayList = new ArrayList<>();
        try {
            PreparedStatement prSt = database.getPreStmt("SELECT * FROM workposition");
            ResultSet rs = prSt.executeQuery();
            while (rs.next()) {
                WorkPosition workPosition = new WorkPosition(rs.getInt(1),rs.getString(2),rs.getInt(3));
                workPositionArrayList.add(workPosition);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return workPositionArrayList;
    }

    @Override
    public WorkPosition get(int id) {
        WorkPosition workPosition = new WorkPosition();
        PreparedStatement prSt = dao.getPreStmt("select * from WorkPosition where PositionId=?");
        try {
            prSt.setInt(1,id);
            ResultSet rs = prSt.executeQuery();
            while (rs.next()){
                workPosition.setPositionId(rs.getInt(1));
                workPosition.setName(rs.getString(2));
                workPosition.setPositionLvl(rs.getInt(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return workPosition;
    }

    @Override
    public WorkPosition create(WorkPosition workPosition) {
        return null;
    }

    @Override
    public void update(WorkPosition workPosition) {

    }

    @Override
    public void delete(WorkPosition workPosition) {

    }

    @Override
    public void deleteById(int id) {

    }
}
