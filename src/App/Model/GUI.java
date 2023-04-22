package App.Model;

import javax.swing.*;

public class GUI {
    private String GUIName;
    private JPanel GUI;

    public GUI(String GUIName, JPanel GUI) {
        this.GUIName = GUIName;
        this.GUI = GUI;
    }
    public GUI(){

    }

    public String getGUIName() {
        return GUIName;
    }

    public void setGUIName(String GUIName) {
        this.GUIName = GUIName;
    }

    public JPanel getGUI() {
        return GUI;
    }

    public void setGUI(JPanel GUI) {
        this.GUI = GUI;
    }
}
