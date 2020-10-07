package client.gui.button;

import javax.swing.*;
import java.awt.*;

public class TransparentButton extends JButton {
    
    private int x [] = {0, 2, 2, 0};
    private int y [] = {0 ,0 , 50, 50};
    private Color transparent = new Color(0,0,0,1);
    private Polygon square = new Polygon(x, y ,4);

    public TransparentButton(int x, int y, int width, int height) {
        this.setBounds(x,y,width, height);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setBackground(new Color(255,255,255,0));
        this.setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        if (!isOpaque() && getBackground().getAlpha() < 255) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        g.setColor(transparent);
        g.drawPolygon(square);
        g.fillPolygon(square);
        super.paintComponent(g);
    }

    public Color getTransparent() {
        return transparent;
    }

    public void setTransparent(Color transparent) {
        this.transparent = transparent;
    }
}
