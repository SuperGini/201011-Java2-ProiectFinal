package client.util;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAdapterMiniButton extends MouseAdapter {

    private Color color1 = new Color(170,170,170,245);
    private Color enterColor = new Color(170,170,170,150);
    private Color noColor  = new Color(255,255,255,0);


    @Override
    public void mousePressed(MouseEvent e) {
        Component button = e.getComponent();
        button.setBackground(color1);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component button = e.getComponent();
        button.setBackground(color1);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Component button = e.getComponent();
        button.setBackground(enterColor);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        Component button = e.getComponent();
        button.setBackground(noColor);

    }
}
