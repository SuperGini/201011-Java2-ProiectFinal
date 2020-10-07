package client.util.focusAdapter;

import client.gui.panel.HorizontalTransparentPanel;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FocusAdapter extends WindowAdapter {

    private HorizontalTransparentPanel upperPanel;
    private HorizontalTransparentPanel lowerPanel;

    public FocusAdapter(HorizontalTransparentPanel upperPanel, HorizontalTransparentPanel lowerPanel) {
        this.upperPanel = upperPanel;
        this.lowerPanel = lowerPanel;
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {

        super.windowGainedFocus(e);
        upperPanel.setColor1(new Color(255,255,255,250));
        upperPanel.repaint();
        lowerPanel.setColor1(new Color(255,255,255,250));
        lowerPanel.repaint();

    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        super.windowLostFocus(e);
        upperPanel.setColor1(new Color(167,32,7,250));
        upperPanel.repaint(); // ca sa repicteze panoul cu noua culoare altfel remane vechea culoare
        lowerPanel.setColor1(new Color(167,32,7,250));
        lowerPanel.repaint();
    }


}
