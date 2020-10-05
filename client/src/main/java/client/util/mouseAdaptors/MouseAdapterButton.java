package client.util.mouseAdaptors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAdapterButton extends MouseAdapter {

   private static Color colorOrange = new Color(167,32,7);
   private static Color colorWhite = new Color(255,255,255,255);

   private final JButton buttonX;

    public MouseAdapterButton(JButton buttonX) {
        this.buttonX = buttonX;
        buttonX.setBackground(Color.WHITE);
        buttonX.setFocusable(false);
        buttonX.setForeground(colorOrange);
        buttonX.setBorder(BorderFactory.createLineBorder(colorOrange));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Component button = e.getComponent();


        button.setForeground(colorWhite);
        button.setBackground(colorOrange);
        buttonX.setBorder(BorderFactory.createLineBorder(colorOrange));

    }

    @Override
    public void mouseExited(MouseEvent e) {
        Component button = e.getComponent();
        button.setForeground(colorOrange);
        button.setBackground(colorWhite);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        buttonX.setForeground(Color.BLACK);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttonX.setForeground(Color.BLACK);

    }

    public static Color getColorOrange() {
        return colorOrange;
    }

    public static void setColorOrange(Color colorOrange) {
        MouseAdapterButton.colorOrange = colorOrange;
    }

    public static Color getColorWhite() {
        return colorWhite;
    }

    public static void setColorWhite(Color colorWhite) {
        MouseAdapterButton.colorWhite = colorWhite;
    }
}
