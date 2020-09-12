package client.gui;

import javax.swing.*;

public class MainFrame extends JFrame {

    private final int width = 1200;
    private final int height = 800;
    private JPanel mainPanel;
    private JLabel backgroundLabel;



    public MainFrame(){

        initFrame();



        setVisible(true);



    }


    private void initFrame(){
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        mainPanel = new JPanel();
        mainPanel.setSize(width,height);
        this.add(mainPanel);
    }






}
