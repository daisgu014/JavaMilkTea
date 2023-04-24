package App.View;

import App.Controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;

public class LoginView extends JPanel {
    private JLabel leftImage, title, usernameLbl, pwdLbl;
    private JTextField usernameTxf;
    private JPasswordField pwdTxf;
    private JButton loginBtn;
    private LoginController controller;
    public static Color primary = new Color(248, 152, 69);
    public static Color lightGrey = new Color(240, 240, 241);
    public static Color lightYellow = new Color(255, 229, 159);
    public static final Font font = new Font("", Font.BOLD, 22);
    public static final Font smallFont = new Font("", Font.BOLD, 14);

    public LoginView() {
        controller = new LoginController();
        initUI();
    }

    public LoginController getController() {
        return controller;
    }

    public void initUI() {
        this.setLayout(new BorderLayout());
        initLeftUI();
        initRightUI();
        handleEvents();
    }

    public void initLeftUI() {
        String path = "src/Assets/Banners/login.png";
        try {
            JPanel p = new JPanel();
            BoxLayout boxLayout = new BoxLayout(p, BoxLayout.Y_AXIS);
            p.setLayout(boxLayout);
            p.setBackground(primary);
            ImageIcon imageIcon = new ImageIcon(new File(path).toURI().toURL());
            Image scaleImage = imageIcon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
            leftImage = new JLabel(new ImageIcon(scaleImage), JLabel.CENTER);
            p.add(leftImage);
            this.add(p, BorderLayout.WEST);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initRightUI() {
        JPanel p = new JPanel(null);
        p.setBackground(Color.white);
        title = new JLabel("Welcome to Java Milk Tea !");
        usernameLbl = new JLabel("Username");
        pwdLbl = new JLabel("Password");
        usernameTxf = new JTextField("superuser");
        pwdTxf = new JPasswordField("123456");
        loginBtn = new JButton("Login");

        title.setBounds(300, 200, 500, 50);
        usernameLbl.setBounds(300, 300, 100, 25);
        usernameTxf.setBounds(300, 325, 250, 40);
        pwdLbl.setBounds(300, 375, 100, 25);
        pwdTxf.setBounds(300, 400, 250, 40);
        loginBtn.setBounds(300, 470, 250, 40);

        title.setFont(font);
        usernameLbl.setFont(smallFont);
        pwdLbl.setFont(smallFont);
        usernameTxf.setBackground(lightGrey);
        pwdTxf.setBackground(lightGrey);
        loginBtn.setBackground(primary);
        loginBtn.setOpaque(true);
        loginBtn.setBorderPainted(false);

        p.add(title);
        p.add(usernameLbl);
        p.add(usernameTxf);
        p.add(pwdLbl);
        p.add(pwdTxf);
        p.add(loginBtn);

        this.add(p, BorderLayout.CENTER);
    }

    public void handleEvents() {
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.checkLogIn(
                        usernameTxf.getText(),
                        String.valueOf(pwdTxf.getPassword())
                );
            }
        });

    }

}
