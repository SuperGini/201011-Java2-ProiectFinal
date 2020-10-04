package client.gui.frame;

import client.gui.panel.StartPanel;
import client.util.MouseAdapterButton;

import javax.swing.*;

public class StartFrame extends JFrame {

    private StartPanel startPanel;
    private JPanel lowerPanel;
    private JButton startButton;

    public StartFrame(){

        initStartFrame();
        setVisible(true);
    }


    public void initStartFrame(){
        setSize(600,600);
        setLocationRelativeTo(null);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setLayout(null);
        initPanel();
        initLowerPanel();
        initStartButton();

    }

    private void initPanel(){
        startPanel = new StartPanel(0,0,600,300);

        add(startPanel);
    }

    private void initLowerPanel(){
        lowerPanel = new JPanel();
        lowerPanel.setBounds(0,300,600,300);
        lowerPanel.setLayout(null);

        add(lowerPanel);
    }

    private void initStartButton(){
        startButton = new JButton("POWER UP");
        startButton.setBounds(200,30,200,40);
        lowerPanel.add(startButton);

        startButton.addMouseListener(new MouseAdapterButton(startButton));
        startButton.addActionListener(ev -> closeWindow());
    }

    private void closeWindow(){
        try{
            dispose();
            new LoadingFrame();
        }finally {
            startPanel.getTimer().stop();
        }
    }
}
