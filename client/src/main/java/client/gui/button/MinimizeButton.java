package client.gui.button;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class MinimizeButton extends JButton {

    private Graphics2D g2;
    private boolean flag;

    private  Color colorOrange = new Color(167,32,7, 250);
    private  BasicStroke wideStroke1 = new BasicStroke(2.5f);
    private Line2D line = new Line2D.Float(7, 15 ,16, 15);
    private Line2D lineX1 = new Line2D.Float(8,8,15,15);
    private Line2D lineX2 = new Line2D.Float(15,8,8,15);

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



          g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (!isOpaque() && getBackground().getAlpha() < 255) {
            g2.setColor(getBackground());
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        g2.setStroke(wideStroke1);
        g2.setColor(colorOrange);


        if(flag){
            g2.draw(lineX1);
            g2.draw(lineX2);
        }

        if(!flag){
            g2.draw(line);
        }

        super.paintComponent(g);


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

    public Line2D getLine() {
        return line;
    }

    public void setLine(Line2D line) {
        this.line = line;
    }
    

    public Line2D getLineX1() {
        return lineX1;
    }

    public void setLineX1(Line2D lineX1) {
        this.lineX1 = lineX1;
    }

    public Line2D getLineX2() {
        return lineX2;
    }

    public void setLineX2(Line2D lineX2) {
        this.lineX2 = lineX2;
    }
}
