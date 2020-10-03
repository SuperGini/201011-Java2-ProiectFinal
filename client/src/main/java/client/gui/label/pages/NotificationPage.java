package client.gui.label.pages;

import client.gui.panel.TransparentPanel;

import javax.swing.*;

public class NotificationPage extends JLabel {

    private JPanel transparentPanel;

    public NotificationPage(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        initTransparentPanel();

    }


    private void initTransparentPanel(){
        transparentPanel = new TransparentPanel(0,0,200,150);
        this.add(transparentPanel);

    }
    
}
