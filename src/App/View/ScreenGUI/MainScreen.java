package App.View.ScreenGUI;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private CardLayout mainLayout;
    private Panel managementScreen;


    public MainScreen() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.mainLayout = new CardLayout();
        this.setLayout(mainLayout);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
