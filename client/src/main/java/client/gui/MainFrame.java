package client.gui;

import client.controller.PictureController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final int width = 1200;
    private final int height = 800;
    private JPanel mainPanel;
    private JLabel backgroundLabel;



    public MainFrame(){

        initFrame();
        initBackgroundLabel();



        setVisible(true);



    }


    private void initFrame(){
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        mainPanel = new JPanel();
        mainPanel.setSize(width,height);
        mainPanel.setLayout(null);
        this.add(mainPanel);
    }

    private void initBackgroundLabel(){
        backgroundLabel = new JLabel();
        backgroundLabel.setSize(width, height);

        byte [] image = PictureController.getInstance().getPicture().getPicture();

        Image rescaleImage = new ImageIcon(image).getImage()
                            .getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(rescaleImage);

        backgroundLabel.setIcon(imageIcon);
        mainPanel.add(backgroundLabel);
        
    }






}
