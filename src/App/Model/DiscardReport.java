package App.Model;

import java.sql.Date;
import java.util.ArrayList;

public class DiscardReport extends Report {
    private ArrayList<DiscardDetail> details;

    public DiscardReport(int reportId, Employee created, Employee confirmed, Date reportDate,
                         ArrayList<DiscardDetail> details) {
        super(reportId, created, confirmed, reportDate);
        this.details = details;
    }

    public ArrayList<DiscardDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<DiscardDetail> details) {
        this.details = details;
    }
}
