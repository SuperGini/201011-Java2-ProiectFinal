package client.gui.label.pages;

import client.gui.button.TransparentButton;
import client.gui.panel.TransparentPanel;
import client.util.sound.SoundPlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class LeftButtonPage extends JLabel {

    private int width, height;

    private JPanel transparentPanel;

    private JButton button;
    private boolean flag = true;

    private String [] buttonName = {"Create Order", "Create client & vehicle", "Parts", "Statistics", "Account" };
    private List<JButton> buttons = new ArrayList<>();
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
        for( int i = 0; i < 5; i++){
            button = new TransparentButton(0, 100 + (i*50), 200,50);
            button.setText(buttonName[i]);
            button.setBackground(new Color(0,0,0,0));
            button.setBorderPainted(false);
            button.setFocusable(false);
            button.setForeground(Color.WHITE);


            button.addMouseListener(new MouseAdapter() {


                @Override
                public void mouseClicked(MouseEvent e) {
                    Component but = e.getComponent();

                    for(JButton b : buttons){
                        if(b.getForeground().equals(Color.BLACK)){
                            b.setForeground(Color.WHITE);
                            b.setBackground(new Color(0,0,0,0));
                          //  soundPlay.getClips().get(1).stop();
                            soundPlay.getClips().get(2).setMicrosecondPosition(0);
                        }
                        if(b == but){
                            but.setForeground(Color.BLACK);
                            but.setBackground(new Color(200,200,200,200));
                            soundPlay.getClips().get(2).start();
                        }
                    }

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                   // soundPlay.getClips().get(2).start();
                    Component but = e.getComponent();
                    if(but.getForeground().equals(Color.BLACK)){
                        but.setBackground(new Color(200,200,200,250));
                    }else{
                        but.setBackground(new Color(170,170,170,150));
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                 //   soundPlay.getClips().get(2).setMicrosecondPosition(0);
                    Component but = e.getComponent();
                    if(but.getForeground().equals(Color.BLACK)){
                        but.setBackground(new Color(200,200,200,200));
                    }else{
                        but.setBackground(new Color(0,0,0,0));
                    }
                }
            });
            buttons.add(button);
            transparentPanel.add(button);
        }
    }

    public List<JButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<JButton> buttons) {
        this.buttons = buttons;
    }
}
