package DAL;

import App.Model.Employee;
import App.Model.IncomeDetail;
import App.Model.IncomeReport;
import App.Model.Ingredient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class IncomeDAO extends DAO<IncomeReport>{

    @Override
    public ArrayList<IncomeReport> getAll() {
        ArrayList<IncomeReport> incomeReports = new ArrayList<>();
        Database db = new Database();
        Statement stmt = db.getStmt();

        try {
            ResultSet rs = stmt.executeQuery("select * from IncomeReports");
            while(rs!=null & rs.next()) {
                incomeReports.add(new IncomeReport(
                        rs.getInt(1),
                        new Employee(),
                        new Employee(),
                        rs.getDate(4),
                        rs.getString(5)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (IncomeReport i: incomeReports) {
            i.setIncomeDetails(this.getDetails(i));
        }
        return incomeReports;
    }

    @Override
    public IncomeReport get(int id) {
        return null;
    }

    public ArrayList<IncomeDetail> getDetails(IncomeReport incomeReport) {
        ArrayList<IncomeDetail> details = new ArrayList<>();
        Database db = new Database();
        IngredientDAO ingredientDAO = new IngredientDAO();
        PreparedStatement preStmt = db.getPreStmt("select * from IncomeDetails where reportID = ?");
        try {
            preStmt.setInt(1, incomeReport.getReportId());
            ResultSet rs = preStmt.executeQuery();
            while(rs!=null && rs.next()) {
                details.add(new IncomeDetail(
                        ingredientDAO.get(rs.getInt(1)),
                        rs.getInt(2),
                        rs.getInt(3)
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return details;
    }

    @Override
    public int create(IncomeReport incomeReport) {
        return 0;
    }

    @Override
    public void update(IncomeReport incomeReport) {

    }

    @Override
    public void delete(IncomeReport incomeReport) {

    }

    @Override
    public void deleteById(int id) {

    }
}
