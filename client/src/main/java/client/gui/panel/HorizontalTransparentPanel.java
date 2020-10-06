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


    Color color1 = new Color(167,32,7,250);



    public HorizontalTransparentPanel(int x, int y, int width, int height, boolean flag) {
        this.setBounds(x, y, width, height);
        this.setBackground(new Color(150 ,150,150,50));
        this.setOpaque(false);// cel mai important ca sa mearga panourile semitransparente
        this.flag = flag;

    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        if (!isOpaque() && getBackground().getAlpha() < 255) {
            g2.setColor(getBackground());
            g2.fillRect(0, 0, getWidth(), getHeight());

        }

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if(flag){
            g2.drawPolygon(trapez);
            g2.setColor(color1);
            g2.fillPolygon(trapez);
        }else{
            g2.drawPolygon(trapez2);
            g2.setColor(color1);
            g2.fillPolygon(trapez2);

        }


        super.paintComponent(g2);
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
