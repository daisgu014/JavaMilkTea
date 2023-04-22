package App.Controller;

import App.Model.ImportModel;

public class ImportController {
    private ImportModel model;

    public ImportController() {
        model = new ImportModel();
    }

    public ImportModel getModel() {
        return model;
    }
}
