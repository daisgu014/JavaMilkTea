package App.View.CrudGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class RoundButton extends JButton {

    public RoundButton(String text, Color color, Color colorHover) {
        super(text);
        setOpaque(true);
        setSize(40,40);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setBackground(color);
        setOpaque(true);
        setBorderPainted(false);
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBackground(colorHover);
            }
            public void mouseExited(MouseEvent e) {
                setBackground(color);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.setColor(getForeground());
        g2.drawString(getText(), getWidth()/2 - g2.getFontMetrics().stringWidth(getText())/2, getHeight()/2 + g2.getFontMetrics().getHeight()/4);
        g2.dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        RoundButton btn = new RoundButton("Click",Color.green,Color.CYAN);
        frame.add(btn);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
