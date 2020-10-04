package client.gui.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class StartPanel extends JPanel {
    Graphics2D g2;

    int count, count2;
    boolean flag = true;

    private int x [] = {310, 310, 325, 300, 275, 290, 290};
    private int y []  = {190, 250, 250, 300, 250, 250, 190};

    private Polygon arow = new Polygon(x, y, 7);

    private Timer timer;




    public StartPanel(int x, int y, int width, int height) {
        this.setBounds(x,y,width,height);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        getArowTimer().start();


    }

    public void paint(Graphics g){
        super.paintComponent(g);
         g2 = (Graphics2D) g;


        if (!isOpaque() && getBackground().getAlpha() < 255) {
            g2.setColor(getBackground());
            g2.fillRect(0, 0, getWidth(), getHeight());

        }

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


        AffineTransform at3 = g2.getTransform();
        AffineTransform rotate3 = AffineTransform.getTranslateInstance(0,-count + count2);
        g2.setTransform(rotate3);
        g2.drawPolygon(arow);
        g2.setColor(Color.RED);
        g2.fillPolygon(arow);
        g2.setTransform(at3);

    }
    private Timer getArowTimer(){
        timer = new Timer(10, ev->  moveArow());
        return timer;
    }

    private void moveArow(){
        repaint();

        if(count >= 0 && flag){
            count++;
        }

        if(count == 100){
            flag = false;
        }

        if(count <= 100 && flag == false){
            count--;
        }

        if(count == 0){
            flag = true;
        }


    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
