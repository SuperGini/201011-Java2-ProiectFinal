package client.gui.frame;

import client.gui.panel.LoadingPanel;
import org.jogamp.glg2d.GLG2DCanvas;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoadingFrame extends JFrame {

    private LoadingPanel panel;
    private int posX =0, posY = 0;


    public LoadingFrame(){
        initFrame();
        this.setVisible(true);
        mouseListener();
    }


    private void initFrame(){
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        panel = new LoadingPanel();

        //linia aceasta face ca grafica sa fie executata pe procesor
      //  add(panel);

        //daca linia de jos face figuri comenteaz-o si folosete-o pe cea de sus care e comentata
        //lini de jos arunca grafica pe placa video (OpenGL)
          this.setContentPane(new GLG2DCanvas(panel));

    }

    private void mouseListener(){
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
