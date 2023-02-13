package App.Model;

import java.sql.Date;
import java.sql.Time;

public class DailyWork {
    private Date dailyDate;
    private Time checkIn;
    private Time checkOut;
    private float workingHours;

    public DailyWork() {
    }

    public DailyWork(Date dailyDate, Time checkIn, Time checkOut, float workingHours) {
        this.dailyDate = dailyDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.workingHours = workingHours;
    }

    public Date getDailyDate() {
        return dailyDate;
    }

    public void setDailyDate(Date dailyDate) {
        this.dailyDate = dailyDate;
    }

    public Time getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Time checkIn) {
        this.checkIn = checkIn;
    }

    public Time getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Time checkOut) {
        this.checkOut = checkOut;
    }

    public float getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(float workingHours) {
        this.workingHours = workingHours;
    }
}
