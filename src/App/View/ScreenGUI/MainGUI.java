package App.View.ScreenGUI;

import App.Model.GUI;
import App.View.LoginView;
import App.View.Shop.ShopGUI;
import Entity.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import static App.View.LoginView.primary;
import static java.util.Arrays.asList;

public class MainGUI extends JFrame {
    ManagementGUI managementGUI;
    JPanel menuButton, contentMain;
    private JLabel logo;

    JScrollPane menuButtonScroll;
    public Employee currEmployee;
    public static final Font smallFont = new Font("", Font.PLAIN, 14);
    HashMap<Integer, ArrayList<String>> roles = new HashMap<>() {{
        put(1, new ArrayList<>(asList(
                "Sale",
                "Customer",
                "Product",
                "Employee",
                "Account",
                "Import",
                "Statistic"
        )));
        put(2, new ArrayList<>(asList(
                "Sale"
        )));
    }};

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
        menuButton = new JPanel(new FlowLayout());
        menuButton.setPreferredSize(new Dimension(225, 1000));
        menuButton.setBackground(primary);
        presentLogo();
        roles.get(roleId).forEach(role -> {
            GUI gui = managementGUI.findByName(role);
            JButton itemMenu = new JButton(gui.getGUIName(), gui.getIcon());
            setUIButton(itemMenu);
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

    public void presentLogo() {
        String path = "src/Assets/Banners/boba.png";
        try {
            ImageIcon imageIcon = new ImageIcon(new File(path).toURI().toURL());
            Image scaleImage = imageIcon.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT);
            logo = new JLabel(new ImageIcon(scaleImage), SwingConstants.CENTER);
            logo.setPreferredSize(new Dimension(100, 100));
            menuButton.add(logo);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showLoginGUI() {
        contentMain.add(new LoginView());
        add(contentMain, BorderLayout.CENTER);
    }

    private void addLogoutBtn() {
        try {
            ImageIcon icon = new ImageIcon(new File("src/Assets/Icons/logout.png").toURI().toURL());
            JButton itemMenu = new JButton("Log out", icon);
            setUIButton(itemMenu);
            itemMenu.setBorder(null);
            menuButton.add(itemMenu);
            itemMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    repaintMainGUI();
                    showLoginGUI();
                }
            });
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

    private void repaintMainGUI() {
        if(menuButton != null) {
            menuButton.removeAll();
            menuButton.setPreferredSize(new Dimension(0, 0));
        }
        contentMain.removeAll();
        contentMain.revalidate();
        contentMain.repaint();
    }

    private void setUIButton(JButton button) {
        button.setPreferredSize(new Dimension(175, 60));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(16);
        button.setBackground(primary);
        button.setBorderPainted(false);
        button.setFont(smallFont);
    }

}
