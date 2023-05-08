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
            PreparedStatement prSt = database.getPreStmt("SELECT * FROM workposition  WHERE DeleteAt is null");
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
        try {
            PreparedStatement prSt = database.getPreStmt("INSERT INTO workposition(WorkPositionName,WorkPositionLVL)  VALUES(?,?)");
            prSt.setString(1, workPosition.getName());
            prSt.setInt(2,workPosition.getPositionLvl());
            prSt.execute();
            PreparedStatement prStSelect = database.getPreStmt("SELECT * FROM workposition WHERE DeleteAt is null");
            ResultSet rs = prStSelect.executeQuery();
            while (rs.next()) {
                workPosition = new WorkPosition(rs.getInt(1),rs.getString(2),rs.getInt(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return workPosition;
    }

    @Override
    public void update(WorkPosition workPosition) {
        try {
            PreparedStatement prSt = database.getPreStmt("UPDATE workposition SET WorkPositionName = ?,WorkPositionLVL=? WHERE PositionId = ?");
            prSt.setString(1,workPosition.getName());
            prSt.setInt(2,workPosition.getPositionLvl());
            prSt.setInt(3,workPosition.getPositionId());
            prSt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(WorkPosition workPosition) {
        try {
            PreparedStatement prSt = database.getPreStmt("UPDATE workposition SET DeleteAt = CURDATE() WHERE PositionId = ?");
            prSt.setInt(1,workPosition.getPositionId());
            prSt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {

    }
}
