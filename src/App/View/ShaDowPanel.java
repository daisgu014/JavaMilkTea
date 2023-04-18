package App.View;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.*;

public class ShaDowPanel extends JPanel
{
    private Color shadowColor = new Color(0,	178,	191);
    private int shadowSize = 5;

    public ShaDowPanel(BorderLayout borderLayout) {
        setBorder(new EmptyBorder(shadowSize, shadowSize, shadowSize, shadowSize));
        setLayout(borderLayout);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        Shape shadow = new Rectangle(shadowSize, shadowSize, width - shadowSize * 2, height - shadowSize * 2);
        g2d.setColor(shadowColor);
        g2d.fill(new RoundRectangle2D.Float(shadow.getBounds().x, shadow.getBounds().y, shadow.getBounds().width, shadow.getBounds().height, 20, 20));
        g2d.dispose();
    }
}
