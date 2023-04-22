package App.View.ScreenGUI;

import App.View.ImportProductView;
import App.View.StatisticView.StatisticView;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private BorderLayout mainLayout;
    private JPanel managementScreen;
    private JPanel statisticView, importView;


    public MainScreen(){
        initGUI();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.mainLayout = new BorderLayout();
        this.setLayout(mainLayout);
        this.add(importView, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public void initGUI() {
        statisticView = new StatisticView();
        importView = new ImportProductView();
    }
}
