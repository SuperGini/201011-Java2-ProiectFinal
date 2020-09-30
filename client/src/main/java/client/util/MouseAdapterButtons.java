package client.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAdapterButtons extends MouseAdapter {

   private Color colorOrange = new Color(167,32,7);
   private Color colorWhite = new Color(255,255,255);


   private JButton buttonx;

    public MouseAdapterButtons(JButton buttonx) {
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
        button.setBackground(Color.white);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        buttonx.setForeground(Color.BLACK);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttonx.setForeground(Color.BLACK);

    }

    public Color getColorOrange() {
        return colorOrange;
    }

    public void setColorOrange(Color colorOrange) {
        this.colorOrange = colorOrange;
    }

    public Color getColorWhite() {
        return colorWhite;
    }

    public void setColorWhite(Color colorWhite) {
        this.colorWhite = colorWhite;
    }

}
