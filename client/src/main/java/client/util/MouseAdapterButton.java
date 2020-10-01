package client.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAdapterButton extends MouseAdapter {

   private static Color colorOrange = new Color(167,32,7);

   private final JButton buttonx;

    public MouseAdapterButton(JButton buttonx) {
        this.buttonx = buttonx;
        buttonx.setBackground(Color.WHITE);
        buttonx.setFocusable(false);
        buttonx.setForeground(colorOrange);
        buttonx.setBorder(BorderFactory.createLineBorder(colorOrange));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Component button = e.getComponent();


        button.setForeground(Color.WHITE);
        button.setBackground(colorOrange);
        buttonx.setBorder(BorderFactory.createLineBorder(colorOrange));

    }

    @Override
    public void mouseExited(MouseEvent e) {
        Component button = e.getComponent();
        button.setForeground(colorOrange);
        button.setBackground(Color.WHITE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        buttonx.setForeground(Color.BLACK);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttonx.setForeground(Color.BLACK);

    }

    public static Color getColorOrange() {
        return colorOrange;
    }
}
