package client.util.windowMovement;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoveFrameWithMouse {

    private int posX;
    private int posY;

    private JFrame frame;

    public MoveFrameWithMouse(JFrame frame) {
        this.frame = frame;
        mouseListener();
    }

    private void mouseListener() {
        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });

        frame.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getXOnScreen() - posX, e.getYOnScreen() - posY);
            }
        });
    }

}
