package client.gui.panel;

import javax.swing.*;
import java.awt.*;

public class HorizontalTransparentPanel extends JPanel {

    private int [] x = {100, 107, 1093, 1100,};
    private int [] y = {0, 9, 9, 0};
    private int [] y2 = {75,66,66,75};

    private boolean flag;

    private Polygon trapez = new Polygon(x, y, 4);
    private Polygon trapez2 = new Polygon(x, y2, 4);


    Color color1 = new Color(175,0,0,80);



    public HorizontalTransparentPanel(int x, int y, int width, int height, boolean flag) {
        this.setBounds(x, y, width, height);
        this.setBackground(new Color(150 ,150,150,50));
        this.setOpaque(false);// cel mai important ca sa mearga panourile semitransparente
        this.flag = flag;

    }

    @Override
    protected void paintComponent(Graphics g) {

        if (!isOpaque() && getBackground().getAlpha() < 255) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());

        }
        if(flag){
            g.drawPolygon(trapez);
            g.setColor(color1);
            g.fillPolygon(trapez);
        }else{
            g.drawPolygon(trapez2);
            g.setColor(color1);
            g.fillPolygon(trapez2);

        }


        super.paintComponent(g);
    }

    public Polygon getTrapez() {
        return trapez;
    }

    public void setTrapez(Polygon trapez) {
        this.trapez = trapez;
    }

    public Polygon getTrapez2() {
        return trapez2;
    }

    public void setTrapez2(Polygon trapez2) {
        this.trapez2 = trapez2;
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }
}
