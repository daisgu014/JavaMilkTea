package App.View.ScreenGUI;

import App.View.StatisticView;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private BorderLayout mainLayout;
    private JPanel managementScreen;
    private JPanel statisticView;


    public MainScreen(){
        initGUI();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.mainLayout = new BorderLayout();
        this.setLayout(mainLayout);
        this.add(statisticView, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public void initGUI() {
        statisticView = new StatisticView();
    }
}
