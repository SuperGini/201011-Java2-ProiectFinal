package client.gui.frame;

import client.gui.panel.StartPanel;
import client.util.mouseAdaptors.MouseAdapterButton;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartFrame extends JFrame {

    private StartPanel startPanel;
    private JPanel lowerPanel;
    private JButton startButton;

    private int posX =0, posY =0;

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
        mouseListener();

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

    private void mouseListener() {
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {

                setLocation(e.getXOnScreen() - posX, e.getYOnScreen() - posY);
            }
        });
    }
}
