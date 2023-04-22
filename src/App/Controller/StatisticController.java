package App.Controller;

import App.Model.StatisticModel;
import org.jfree.data.general.DefaultPieDataset;

import java.sql.Date;

public class StatisticController {
    private StatisticModel model;

    public StatisticController() {
        model = new StatisticModel();
    }

    public boolean dateLegal(Date from, Date to) {
        return from != null && to != null && from.compareTo(to) <= 0;
    }

    public void getDataFromTime(Date from, Date to) throws Exception {
        boolean legal = dateLegal(from, to);
        if(!legal) {
            throw new Exception("Invalid Date");
        } else {
            model.getData(from, to);
        }
    }

    public StatisticModel getModel() {
        return model;
    }
}
