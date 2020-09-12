package client.gui.label;

import client.gui.panel.TransparentPanel;

import javax.swing.*;

public class LoginPage extends JLabel {

    private JPanel transparentPanel;

    public LoginPage(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);

    }

    private void initTransparentPanel(){
        transparentPanel = new TransparentPanel(200, 100, 400, 600);
        this.add(transparentPanel);

    }






}
