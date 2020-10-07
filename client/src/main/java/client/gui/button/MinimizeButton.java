package client.gui.button;

import javax.swing.*;
import java.awt.*;

public class MinimizeButton extends JButton {

    private Graphics2D g2;
    private boolean flag;

    private  Color colorOrange = new Color(167,32,7, 250);
    private  BasicStroke wideStroke1 = new BasicStroke(2.5f);

    public MinimizeButton(int x, int y, int width, int height, boolean flag){
        this.flag = flag;
        this.setBounds(x,y,width, height);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setBackground(new Color(255,255,255,0));
        this.setContentAreaFilled(false);
    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

          g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (!isOpaque() && getBackground().getAlpha() < 255) {
            g2.setColor(getBackground());
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        if(flag){
            graphicClose(g2);
        }

        if(!flag){
            graphicMinimize(g2);
        }

    }

    public Graphics2D graphicClose(Graphics2D graphics2D){
        graphics2D.setColor(colorOrange);
        graphics2D.setStroke(wideStroke1);
        graphics2D.drawLine(8,8,15,15);
        graphics2D.drawLine(15,8,8,15);

        return graphics2D;
    }

    public Graphics2D graphicMinimize(Graphics2D graphics2D){
        graphics2D.setStroke(wideStroke1);
        graphics2D.setColor(colorOrange);
        graphics2D.drawLine(7, 15 ,16, 15 );
        return graphics2D;
    }


    public Graphics2D getG2() {
        return g2;
    }

    public void setG2(Graphics2D g2) {
        this.g2 = g2;
    }


    public Color getColorOrange() {
        return colorOrange;
    }

    public void setColorOrange(Color colorOrange) {
        this.colorOrange = colorOrange;
    }




}
