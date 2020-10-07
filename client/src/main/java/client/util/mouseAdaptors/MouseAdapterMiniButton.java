package client.util.mouseAdaptors;

import client.gui.button.MinimizeButton;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAdapterMiniButton extends MouseAdapter {

    private Color color1 = new Color(170,170,170,250);
    private Color enterColor = new Color(170,170,170,150);
    private Color noColor  = new Color(255,255,255,0);
    private MinimizeButton miniButton;


    public MouseAdapterMiniButton(MinimizeButton miniButton) {
        this.miniButton = miniButton;
    }

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
        miniButton.setColorOrange(Color.WHITE);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        Component button = e.getComponent();
        button.setBackground(noColor);
        miniButton.setColorOrange(MouseAdapterButton.getColorOrange());

    }
}
