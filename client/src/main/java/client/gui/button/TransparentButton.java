package client.gui.button;

import javax.swing.*;
import java.awt.*;

public class TransparentButton extends JButton {
    

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

        super.paintComponent(g);
    }


}
