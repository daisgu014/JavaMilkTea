package App.View.ScreenGUI;

import App.Model.GUI;
import App.View.LoginView;
import App.View.Shop.ShopGUI;
import Entity.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import static java.util.Arrays.asList;

public class MainGUI extends JFrame {
    ManagementGUI managementGUI;
    HashMap<Integer, ArrayList<String>> roles = new HashMap<>() {{
        put(1, new ArrayList<>(asList(
                "Sale",
                "Customer",
                "Statistic"
        )));
        put(2, new ArrayList<>(asList(
                "Sale"
        )));
    }};
    JPanel menuButton, contentMain;

    ScrollPane menuButtonScroll;

    public static Employee currEmployee;

    public MainGUI() {
        setLayout(new BorderLayout());
        contentMain = new JPanel(new BorderLayout());
        showLoginGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void getButton(int roleId) {
        repaintMainGUI();
        managementGUI = new ManagementGUI();
        contentMain.add(new ShopGUI(), BorderLayout.CENTER);
        menuButton = new JPanel(new GridLayout(0, 1));
        roles.get(roleId).forEach(role -> {
            GUI gui = managementGUI.findByName(role);
            Button itemMenu = new Button(gui.getGUIName());
            menuButton.add(itemMenu);

            itemMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (contentMain != null) {
                        contentMain.removeAll();
                    }
                    JPanel newPanel = gui.getGUI();
                    contentMain.add(newPanel, BorderLayout.CENTER);
                    contentMain.revalidate();
                    contentMain.repaint();
                }
            });
        });
        addLogoutBtn();
        add(menuButton, BorderLayout.WEST);
        add(contentMain, BorderLayout.CENTER);
    }

    public void showLoginGUI() {
        contentMain.add(new LoginView());
        add(contentMain, BorderLayout.CENTER);
    }

    private void addLogoutBtn() {
        Button itemMenu = new Button("Log out");
        menuButton.add(itemMenu);
        itemMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaintMainGUI();
                showLoginGUI();
            }
        });
    }

    private void repaintMainGUI() {
        if(menuButton != null) {
            menuButton.removeAll();
        }
        contentMain.removeAll();
        contentMain.revalidate();
        contentMain.repaint();
    }

}
