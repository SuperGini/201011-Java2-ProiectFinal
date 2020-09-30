package client.util;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAdapterButtons extends MouseAdapter {

   private Color colorOrange = new Color(167,32,7);
   private Color colorWhite = new Color(255,255,255);
   private Color transparent = new Color(0,0,0,0);


    @Override
    public void mouseEntered(MouseEvent e) {
        Component button = e.getComponent();
        button.setForeground(colorWhite);
        button.setBackground(colorOrange);


    }

    @Override
    public void mouseExited(MouseEvent e) {
        Component button = e.getComponent();
        button.setForeground(colorOrange);
        button.setBackground(transparent);

    }
}
