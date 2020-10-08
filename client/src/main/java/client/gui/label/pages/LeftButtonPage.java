package client.gui.label.pages;

import client.gui.button.TransparentButton;
import client.gui.panel.TransparentPanel;
import client.util.sound.SoundPlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class LeftButtonPage extends JLabel {

    private int width, height;

    private JPanel transparentPanel;
    private Color orange = new Color(167,32,7, 250);
    private Color transparent = new Color(0,0,0,1);
    private TransparentButton button;
    private boolean flag = true;

    private String [] buttonName = {"Create Order", "Create client & vehicle", "Parts", "Account" };
    private List<TransparentButton> buttons = new ArrayList<>();
    private static SoundPlay soundPlay = new SoundPlay();


    public LeftButtonPage(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        this.setBounds(x, y, width, height);
        initTransparentPanel();
        initButtons();

    }

    private void initTransparentPanel(){
        transparentPanel = new TransparentPanel(0, 0, width, height ){
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBackground().getAlpha() < 255) {
                    g.setColor(getBackground());
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        transparentPanel.setLayout(null);
        transparentPanel.setOpaque(false);
        transparentPanel.setBackground(new Color(150 ,150,150,150));
        this.add(transparentPanel);
    }

    private void initButtons(){
        for( int i = 0; i < 4; i++){
            button = new TransparentButton(0, 100 + (i*50), 200,50);
            button.setText(buttonName[i]);
            button.setBackground(new Color(0,0,0,0));
            button.setBorderPainted(false);
            button.setFocusable(false);
            button.setForeground(Color.WHITE);

            button.addActionListener(this::buttonGraphics);

            button.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    Component but = e.getComponent();
                    if(!but.getForeground().equals(Color.BLACK)){
                        but.setBackground(new Color(170,170,170,150));
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    Component but = e.getComponent();
                    if(!but.getForeground().equals(Color.BLACK)){
                        but.setBackground(new Color(0,0,0,0));
                    }
                }
            });

            buttons.add(button);
            transparentPanel.add(button);
        }
    }

    private void buttonGraphics(ActionEvent ev){

        TransparentButton but = (TransparentButton) ev.getSource();
        if(!but.getForeground().equals(Color.BLACK)){

            for(TransparentButton b : buttons) {
                if (b.getForeground().equals(Color.BLACK)) {
                    b.setForeground(Color.WHITE);
                    b.setBackground(new Color(0, 0, 0, 0));
                    b.setTransparent(transparent);
                    soundPlay.getClips().get(0).setMicrosecondPosition(0); //daca se blocheaza cand selectezi butoanele comenteaza linia asta si cea de jos
                }
            }

            if (but.getForeground().equals(Color.WHITE) && but.getBackground().equals(new Color(170, 170, 170, 150))) {
                but.setForeground(Color.BLACK);
                but.setBackground(new Color(200, 200, 200, 220));
                but.setTransparent(orange);
                soundPlay.getClips().get(0).start(); //daca se blocheaza interfata grafica comenteaza linia asta
            }
        }
    }

    public List<TransparentButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<TransparentButton> buttons) {
        this.buttons = buttons;
    }
}
