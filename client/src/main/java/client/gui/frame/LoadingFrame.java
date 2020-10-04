package client.gui.frame;

import client.gui.panel.LoadingPanel;
import org.jogamp.glg2d.GLG2DCanvas;

import javax.swing.*;

public class LoadingFrame extends JFrame {

    private LoadingPanel panel;
  //  private Canv canv;


    public LoadingFrame(){
        initFrame();
        this.setVisible(true);

    }


    private void initFrame(){
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        panel = new LoadingPanel();
     //   canv = new Canv();

      //  add(panel);

          this.setContentPane(new GLG2DCanvas(panel));




    }
}
