package client.gui.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;


public class LoadingPanel extends JPanel {



    int firstCircleAngle,secondCircleAngle, secondCircleCount, writingCount, dotCount, linesCount;

    int mainFrameVisibleCount;

    BasicStroke wideStroke = new BasicStroke(10.0f);

    boolean secondCircleVariableBar = true;
    private java.util.List<Line2D> litleLines = new ArrayList<>();

    public LoadingPanel() {
        this.setBounds(0,0,600,600);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        addLinesToList();


    }



    public void paint (Graphics g) {
        super.paintComponent(g);


        Graphics2D  g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        drawText(g2);
        staticOrangeLines(g2);
        firstCircel(g2);
        secondCircle(g2);
        movingOrangeLines(g2);
        thirdCircle(g2);

        animatie1.start();
        animatie2.start();
        animatie3.start();
        animatie4.start();

    }

    //barile cyan
    Timer animatie1 = new Timer(20, e -> repaint());

    //cifrele incarcare loading
    Timer animatie2 = new Timer(80,e -> animatie2());

    Timer animatie3 = new Timer(20,e -> animatie3());

    Timer animatie4 = new Timer(10, e -> animatie4());

    private void animatie2(){
        if(writingCount < 100){
            writingCount++;
            dotCount++; // face punctuletele sa apara si sa dispara
        }

        secondCircleAngle++;
      //  System.out.println(secondCircleAngle++);


        if(mainFrameVisibleCount <= 119){
            mainFrameVisibleCount ++;
        }
    }

    //barile rosii cresc si scad


    private void animatie3(){
        if(secondCircleVariableBar == true && secondCircleCount < 60){

            secondCircleCount++;

            if(secondCircleCount == 60){
                secondCircleVariableBar = false;
            }
        }

        if(secondCircleVariableBar == false && secondCircleCount > 0){

            secondCircleCount--;

            if(secondCircleCount == 0){
                secondCircleVariableBar = true;
            }
        }
    }



    private void animatie4(){
        if(writingCount < 50 && linesCount < 90){ //liniutele pleaca
            linesCount++;
        }

        if(writingCount > 50 && linesCount > 0){ // liniutele se intorc
            linesCount--;
        }
        firstCircleAngle++;
    }


    private void staticOrangeLines(Graphics2D g2){
        g2.setColor(Color.orange);

        g2.drawLine(450,300,460,300);
        g2.drawLine(460,295,460,305);

        g2.drawLine(300,150,300,140);
        g2.drawLine(295,140,305,140);

        g2.drawLine(150,300,140,300);
        g2.drawLine(140,295,140,305);

        g2.drawLine(300,450,300,460);
        g2.drawLine(295,460,305,460);
    }

    //method 1
    private void addLinesToList(){
        for(int x = 0; x <= 90; x+=5) {

            Line2D line = new Line2D.Double(
                    Math.round(300 + (145 * Math.cos(Math.toRadians(x)))),
                    Math.round(300 - (145 * Math.sin(Math.toRadians(x)))),
                    Math.round(300 + (155 * Math.cos(Math.toRadians(x)))),
                    Math.round(300 - (155 * Math.sin(Math.toRadians(x)))));
            litleLines.add(line);

        }
    }

    //metod 2
    private void movingOrangeLines(Graphics2D g2){

        AffineTransform at3 = g2.getTransform();
        AffineTransform rotate3 = AffineTransform.getRotateInstance(Math.toRadians(linesCount), 300,300);
        g2.setTransform(rotate3);

        BasicStroke wideStroke1 = new BasicStroke(1f);
        g2.setStroke(wideStroke1);
        g2.setColor(Color.ORANGE);

        litleLines.forEach(g2::draw);

        g2.setTransform(at3);
    }

    private void firstCircel(Graphics2D g2){

        g2.setColor(Color.CYAN);
        g2.setStroke(wideStroke);

        AffineTransform at = g2.getTransform();
        AffineTransform rotate = AffineTransform.getRotateInstance(Math.toRadians(-firstCircleAngle), 300,300);
        g2.setTransform(rotate);


        g2.drawArc(210, 210, 180, 180, 15, 60);
        g2.drawArc(210, 210, 180, 180, 105, 60);
        g2.drawArc(210, 210, 180, 180, 195, 60);
        g2.drawArc(210, 210, 180, 180, 285, 60);
        g2.setTransform(at);
    }

    private void secondCircle(Graphics2D g2){

        g2.setColor(Color.RED);
        g2.setStroke(wideStroke);
        AffineTransform at2 = g2.getTransform();
        AffineTransform rotate2 = AffineTransform.getRotateInstance(Math.toRadians(-secondCircleAngle), 300,300);
        g2.setTransform(rotate2);

        g2.drawArc(190, 190, 220, 220, 30+ secondCircleCount, 120 - 2* secondCircleCount);
        g2.drawArc(190, 190, 220, 220, 210+ secondCircleCount, 120 - 2* secondCircleCount);
        g2.drawArc(190, 190, 220, 220, 0- secondCircleCount, 0 + 2* secondCircleCount);
        g2.drawArc(190, 190, 220, 220, 180- secondCircleCount, 0 + 2* secondCircleCount);

        g2.setTransform(at2);
    }

    private void thirdCircle(Graphics2D g2){

        AffineTransform at = g2.getTransform();
        AffineTransform rotate = AffineTransform.getRotateInstance(Math.toRadians(firstCircleAngle), 300,300);
        g2.setTransform(rotate);
        g2.setStroke(wideStroke);
        g2.setColor(Color.CYAN);
        g2.setStroke(wideStroke);
        g2.drawArc(170, 170, 260, 260, 337, 45);
       // g2.drawArc(200, 200, 200, 200, 90, 60);
        g2.drawArc(170, 170, 260, 260, 157, 45);
      //  g2.drawArc(200, 200, 200, 200, 270, 60);

        g2.setTransform(at);
    }

    private void drawText(Graphics2D g2){
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Serif", Font.BOLD, 30));
        g2.drawString((writingCount + "%"), 280,300);
        g2.drawLine(220,310,380,310);


        if(writingCount < 100){
            g2.setFont(new Font("Serif", Font.BOLD, 17));
            g2.drawString("loading", 220,325);
        }else{
            g2.drawString("", 220,325);
        }

        if(writingCount > 0 && writingCount < 10){
            g2.drawString("database", 280,350);
        }

        if(writingCount > 10 && writingCount < 20){
            g2.drawString("images", 280,350);
        }

        if(writingCount > 20 && writingCount < 30){
            g2.drawString("geometry", 280,350);
        }

        if(writingCount > 30 && writingCount < 40){
            g2.drawString("textures", 280,350);
        }

        if(writingCount > 40 && writingCount < 60){
            g2.drawString("animation", 280,350);
        }

        if(writingCount > 60 && writingCount < 80){
            g2.drawString("syncing audio", 265,350);
        }

        if(writingCount > 80 && writingCount < 99){
            g2.drawString("cleaning up", 280,350);
        }

        g2.setFont(new Font("Serif", Font.BOLD, 24));

        if(dotCount > 2){
            g2.drawString(".", 280,325);

        }

        if(dotCount > 4){
            g2.drawString(".", 285,325);
        }

        if(dotCount > 6){
            g2.drawString(".", 290,325);
        }

        if(dotCount > 8){
            g2.drawString(".", 295,325);

        }

        if(dotCount == 10){
            dotCount =0;

        }

        if(writingCount == 100){
            g2.setFont(new Font("Serif", Font.BOLD, 17));
            g2.drawString("powerd by", 225,325);
            g2.drawString("Ginitoru", 280,350);
        }
    }


    public Timer getAnimatie1() {
        return animatie1;
    }

    public Timer getAnimatie2() {
        return animatie2;
    }

    public Timer getAnimatie3() {
        return animatie3;
    }

    public Timer getAnimatie4() {
        return animatie4;
    }

    public int getMainFrameVisibleCount() {
        return mainFrameVisibleCount;
    }
}
