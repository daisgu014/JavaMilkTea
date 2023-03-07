package App.Model;

import java.sql.Date;
import java.util.ArrayList;

public class IncomeReport extends Report{
    private String stateReport;
    private ArrayList<IncomeDetail> incomeDetails;

    public IncomeReport(int reportId, Employee created, Employee confirmed, Date reportDate, String stateReport) {
        super(reportId, created, confirmed, reportDate);
        this.stateReport = stateReport;
    }

    public IncomeReport(int reportId, Employee created, Employee confirmed, Date reportDate,
                        String stateReport, ArrayList<IncomeDetail> incomeDetails) {
        super(reportId, created, confirmed, reportDate);
        this.stateReport = stateReport;
        this.incomeDetails = incomeDetails;
    }

    public IncomeReport(Employee created, Employee confirmed, Date reportDate, String stateReport) {
        super(created, confirmed, reportDate);
        this.stateReport = stateReport;
    }

    public String getStateReport() {
        return stateReport;
    }

    public void setStateReport(String stateReport) {
        this.stateReport = stateReport;
    }

    public ArrayList<IncomeDetail> getIncomeDetails() {
        return incomeDetails;
    }

    public void setIncomeDetails(ArrayList<IncomeDetail> incomeDetails) {
        this.incomeDetails = incomeDetails;
    }
}
