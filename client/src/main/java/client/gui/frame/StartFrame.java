package client.gui.frame;

import client.gui.button.MinimizeButton;
import client.gui.button.ZeeButton;
import client.gui.panel.StartPanel;
import client.util.mouseAdaptors.MouseAdapterMiniButton;
import client.util.windowMovement.MoveFrameWithMouse;

import javax.swing.*;

public class StartFrame extends JFrame {

    private MoveFrameWithMouse frameMove;
    private StartPanel startPanel;
    private JPanel lowerPanel;
    private JButton startButton;
    private MinimizeButton minimizeButton;
    private MinimizeButton closeButton;

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
        initminiButtons();
        initMoveFrameWithMouse();
    }

    private void initMoveFrameWithMouse(){
        frameMove = new MoveFrameWithMouse(this);
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
        startButton = new ZeeButton(200,30,200,40, "POWER UP");
        lowerPanel.add(startButton);
        startButton.addActionListener(ev -> closeWindow());
    }

    private void initminiButtons(){
        minimizeButton = new MinimizeButton(546,0,24,24, false);
        minimizeButton.addMouseListener(new MouseAdapterMiniButton(minimizeButton, false));
        lowerPanel.add(minimizeButton);

        minimizeButton.addActionListener(ev -> setExtendedState(JFrame.ICONIFIED));


        closeButton = new MinimizeButton(570,0,24,24, true);
        closeButton.addMouseListener(new MouseAdapterMiniButton(closeButton, true));
        lowerPanel.add(closeButton);

        closeButton.addActionListener(ev -> closeByX());
    }

    private void closeWindow(){
        try{
            dispose();
            new LoadingFrame();
        }finally {
            startPanel.getTimer().stop();
        }
    }

    private void closeByX(){
        try{
            dispose();
        }finally {
            startPanel.getTimer().stop();
        }
    }
}
