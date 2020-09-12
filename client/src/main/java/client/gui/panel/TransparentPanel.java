package client.gui.panel;

import javax.swing.*;
import java.awt.*;

public class TransparentPanel extends JPanel {

    public TransparentPanel(int x, int y, int width, int height) {
        this.setBounds(x,y,width, height);
        this.setBackground(new Color(255,255,255,100));
        this.setOpaque(false);
        this.setLayout(null);

    }




}
