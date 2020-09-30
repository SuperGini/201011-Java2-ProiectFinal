package client.util;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class MouseAdapterLogAndRegister extends MouseAdapter {
    Font noUnderline = new Font("Dialog", Font.PLAIN, 12);

    public Font underlineFont(){
        Map<TextAttribute, Integer> textAtributtes = new HashMap<>();
        textAtributtes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

        return new Font("Dialog", Font.BOLD, 12).deriveFont(textAtributtes);
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        Component button = e.getComponent();
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(underlineFont());

    }

    @Override
    public void mouseExited(MouseEvent e) {
        Component button = e.getComponent();
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(noUnderline);
    }
}
