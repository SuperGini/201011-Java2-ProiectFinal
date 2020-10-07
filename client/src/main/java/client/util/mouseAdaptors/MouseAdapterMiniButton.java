package client.util.mouseAdaptors;

import client.gui.button.MinimizeButton;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class MouseAdapterMiniButton extends MouseAdapter {

    private MinimizeButton miniButton;
    private boolean flag;


    private Line2D enterXLin1 = new Line2D.Float(10,10,17,17);
    private Line2D enterXLin2 = new Line2D.Float(17,10,10,17);

    private Line2D exitXLine1 = new Line2D.Float(8,8,15,15);
    private Line2D exitXLine2 = new Line2D.Float(15,8,8,15);

    private Line2D enterMiniLine = new Line2D.Float(7,17,16,17);
    private Line2D exitMiniLine = new Line2D.Float(7, 15 ,16, 15);




    public MouseAdapterMiniButton(MinimizeButton miniButton, boolean flag) {
        this.miniButton = miniButton;
        this.flag = flag;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        miniButton.setColorOrange(Color.WHITE);

        if(flag){
            miniButton.setLineX1(enterXLin1);
            miniButton.setLineX2(enterXLin2);
        }

        if(!flag){
            miniButton.setLine(enterMiniLine);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        miniButton.setColorOrange(MouseAdapterButton.getColorOrange());

        if(flag){
            miniButton.setLineX1(exitXLine1);
            miniButton.setLineX2(exitXLine2);
        }

        if(!flag){
            miniButton.setLine(exitMiniLine);
        }
    }
}
