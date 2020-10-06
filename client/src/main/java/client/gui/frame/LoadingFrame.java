package client.gui.frame;

import client.gui.panel.LoadingPanel;
import org.jogamp.glg2d.GLG2DCanvas;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoadingFrame extends JFrame {

    private MainFrame mainFrame;
    private LoadingPanel panel;
    private int posX =0, posY = 0;
    private GLG2DCanvas graphic;

    public LoadingFrame(){
        initFrame();
        this.setVisible(true);
        mouseListener();

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

    private static final class SingletonHolder{
        public static final LoadingFrame INSTANCE = new LoadingFrame();
    }

    public static LoadingFrame getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
