package client.gui.button;

import client.util.mouseAdaptors.MouseAdapterButton;

import javax.swing.*;

public class ZeeButton extends JButton {

    public ZeeButton(int x , int y, int width, int height, String buttonName) {
        setBounds(x, y, width, height);
        setText(buttonName);
        addMouseListener(new MouseAdapterButton(this));
    }
}
