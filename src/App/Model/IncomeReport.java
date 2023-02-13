package App.Model;

import java.sql.Date;

public class IncomeReport extends Report{
    private String stateReport;

    public IncomeReport(int reportId, Employee created, Employee confirmed,
                        Date reportDate, String stateReport) {
        super(reportId, created, confirmed, reportDate);
        this.stateReport = stateReport;
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

}
