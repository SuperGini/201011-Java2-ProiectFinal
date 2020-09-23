package client.gui.label.pages;

import client.gui.panel.TransparentPanel;

import javax.swing.*;

public class StatisticsPage extends JLabel {

    private JPanel transparentPanel;

    public StatisticsPage(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        initTransparentPanel();
    }

    private void initTransparentPanel(){
        transparentPanel = new TransparentPanel(250,125,950,550);
        this.add(transparentPanel);
    }

}
