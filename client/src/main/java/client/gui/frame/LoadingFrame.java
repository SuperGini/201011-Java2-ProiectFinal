package client.gui.frame;

import client.gui.panel.LoadingPanel;
import client.util.windowMovement.MoveFrameWithMouse;
import org.jogamp.glg2d.GLG2DCanvas;

import javax.swing.*;

public class LoadingFrame extends JFrame {

    private MoveFrameWithMouse frameMove;
    private MainFrame mainFrame;
    private LoadingPanel panel;
    private int posX =0, posY = 0;
    private GLG2DCanvas graphic;



    public LoadingFrame(){
        initFrame();
        initMoveFrameWithMouse();
        this.setVisible(true);
        mainFrame = MainFrame.getInstance();

    }


    private void initFrame(){
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        panel = new LoadingPanel();
      //  graphic = new GLG2DCanvas(panel);


        //linia aceasta face ca grafica sa fie executata pe procesor
        add(panel);

        //daca linia de jos face figuri comenteaz-o si folosete-o pe cea de sus care e comentata
        //linia de jos arunca grafica pe placa video (OpenGL) -> problema este ca ramne threadul deshis si nu stiu cum sa-l inchid;)
        //  this.setContentPane(graphic);

        animatie.start();


    }

    Timer animatie = new Timer(1000, e -> verif() );

    private void verif(){
        if(panel.getMainFrameVisibleCount() == 120){

            try{

                mainFrame.setVisible(true);
                dispose();

            }finally{
                panel.getAnimatie1().stop();
                panel.getAnimatie2().stop();
                panel.getAnimatie3().stop();
                panel.getAnimatie4().stop();
                animatie.stop();
            }
        }
    }

    private void initMoveFrameWithMouse(){
        frameMove = new MoveFrameWithMouse(this);
    }
}
