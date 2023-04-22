package App.View.ScreenGUI;

import App.View.Shop.ShopGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    ManagementGUI managementGUI = new ManagementGUI();
    JPanel MenuButton;
    ScrollPane menuButtonScroll;
    public MainGUI(){
        setSize(1200,1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel contentMain = new JPanel(new BorderLayout());
        contentMain.add(new ShopGUI(),BorderLayout.CENTER);
        MenuButton= new JPanel(new GridLayout(0,1));
        managementGUI.getGUISale().forEach(gui->{
            Button itemMenu = new Button(gui.getGUIName());
            MenuButton.add(itemMenu);

            itemMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(contentMain!=null){
                        contentMain.removeAll();
                    }
                    JPanel newPanel = gui.getGUI();
                    contentMain.add(newPanel,BorderLayout.CENTER);
                    contentMain.revalidate();
                    contentMain.repaint();
                }
            });
        });
        add(MenuButton,BorderLayout.WEST);
        add(contentMain,BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
