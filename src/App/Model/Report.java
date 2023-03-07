package App.Model;

import java.sql.Date;

public abstract class Report {
    protected int reportId;
    protected Employee created;
    protected Employee confirmed;
    protected Date reportDate;

    public Report() {
    }

    public Report(int reportId, Employee created, Employee confirmed, Date reportDate) {
        this.reportId = reportId;
        this.created = created;
        this.confirmed = confirmed;
        this.reportDate = reportDate;
    }

    public Report(Employee created, Employee confirmed, Date reportDate) {
        this.created = created;
        this.confirmed = confirmed;
        this.reportDate = reportDate;
    }

    public int getReportId() {
        return reportId;
    }

    public Employee getCreated() {
        return created;
    }

    public void setCreated(Employee created) {
        this.created = created;
    }

    public Employee getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Employee confirmed) {
        this.confirmed = confirmed;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
}
