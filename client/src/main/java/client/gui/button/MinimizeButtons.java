package client.gui.button;

import javax.swing.*;
import java.awt.*;

public class MinimizeButtons extends JButton {

    public MinimizeButtons(int x, int y, int width, int height){
        this.setBounds(x,y,width, height);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setBackground(new Color(255,255,255,0));
        this.setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D  g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (!isOpaque() && getBackground().getAlpha() < 255) {
            g2.setColor(getBackground());
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

    }

}
