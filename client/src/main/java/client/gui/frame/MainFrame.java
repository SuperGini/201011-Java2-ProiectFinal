package client.gui.frame;

import AppPackage.AnimationClass;
import client.controller.media.PictureController;
import client.gui.label.MovingLabel;
import client.gui.label.pages.LeftButtonPage;
import client.gui.label.pages.LoginPage;
import client.gui.label.pages.RegisterPage;
import client.gui.panel.HorizontalTransparentPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainFrame extends JFrame {

    private final int width = 1200;
    private final int height = 800;
    private int posX = 0, posY =0;
    private JPanel mainPanel;
    private JLabel backgroundLabel;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private MovingLabel upperLabel, lowerLabel;
    private LeftButtonPage leftButtonPage;
    private HorizontalTransparentPanel upperPanel, lowerPanel;

    private ScheduledExecutorService randomPicture = Executors.newSingleThreadScheduledExecutor();
    private static AnimationClass slideEfect = new AnimationClass();

    private List<JLabel> pages;



    public MainFrame(){

        initFrame();
        initBackgroundLabel();
        mouseListener();
        initLoginPage();
        initRegisterPage();
        getPages();
        moveLAbelLeft();
        initUpperLabelAndPanel();
        initLowerLabelAndPanel();
        initLeftButtonPage();

        changeFocus();
        setVisible(true);


    }


    private void initFrame(){
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        mainPanel = new JPanel();
        mainPanel.setSize(width,height);
        mainPanel.setLayout(null);
        this.add(mainPanel);
    }

    private void initBackgroundLabel(){
        backgroundLabel = new JLabel();
        backgroundLabel.setSize(width, height);
        scheduleWithFixedDelay();
        mainPanel.add(backgroundLabel);

    }

    private void initLoginPage(){
        loginPage = new LoginPage(1200,0,1200,800);
        backgroundLabel.add(loginPage);

        loginPage.getLoginButton().addActionListener(ev ->{
            if(loginPage.validCredentials()){

            }
        });

        loginPage.getRegisterButton().addActionListener(ev -> {
            moveLabesLeftAndRaight(registerPage);



        });
    }

    private void initRegisterPage(){
        registerPage = new RegisterPage(1200,0,1200,800);
        backgroundLabel.add(registerPage);

        registerPage.getRegisterButton().addActionListener(ev ->{
            if(!registerPage.addUser()){
                System.out.println("a fost creat un user");
            }

        });

        registerPage.getLoginButton().addActionListener(ev -> {
            moveLabesLeftAndRaight(loginPage);
        });
    }

    //method 1
    private void scheduleWithFixedDelay(){
        Runnable task = () -> backgroundLabel.setIcon(getImageIcon());
        randomPicture.scheduleWithFixedDelay(task,0,10, TimeUnit.SECONDS);
    }

    // method 2
    private ImageIcon getImageIcon(){
        byte [] image = PictureController.getInstance().getPicture().getPicture();

        Image rescaleImage = new ImageIcon(image).getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(rescaleImage);
    }

    private void initUpperLabelAndPanel(){
        upperLabel = new MovingLabel(0,0,1200,75);
        backgroundLabel.add(upperLabel);

        upperPanel = new HorizontalTransparentPanel(0,0,1200,75, true);
        upperLabel.add(upperPanel);
    }

    private void initLowerLabelAndPanel(){
        lowerLabel = new MovingLabel(0,725,1200,75);
        backgroundLabel.add(lowerLabel);
        System.out.println("xxx");
        lowerPanel = new HorizontalTransparentPanel(0,0,1200,75, false);
        lowerLabel.add(lowerPanel);

    }

    private void initLeftButtonPage(){
        leftButtonPage = new LeftButtonPage(0,75,200,650);
        backgroundLabel.add(leftButtonPage);
    }









    private List<JLabel> getPages(){
        pages = new ArrayList<>();
        pages.add(loginPage);
        pages.add(registerPage);
        return pages;
    }

    private void moveLabesLeftAndRaight(JLabel labelPage){
        for(JLabel page: pages){
            if((page.getX() == 400)  && (page != labelPage)){
                slideEfect.jLabelXRight(400,1200,2,4,page);
                slideEfect.jLabelXLeft(1200,400,2,4,labelPage);

            }
        }
    }

    private void moveLAbelLeft(){
        slideEfect.jLabelXLeft(1200,400,2,4,loginPage);
    }














    private void mouseListener() {
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {

                setLocation(e.getXOnScreen() - posX, e.getYOnScreen() - posY);
            }
        });
    }



    private void changeFocus(){


        this.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

                super.windowGainedFocus(e);
                System.out.println("Focus");
                upperPanel.setColor1(new Color(0,175,0,80));
                upperPanel.repaint();
                lowerPanel.setColor1(new Color(0,175,0,80));
                lowerPanel.repaint();

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                super.windowLostFocus(e);
                System.out.println("no focus");
                upperPanel.setColor1(new Color(175,0,0,80));
                upperPanel.repaint(); // ca sa repicteze panoul cu noua culoare altfel remane vechea culoare
                lowerPanel.setColor1(new Color(175,0,0,80));
                lowerPanel.repaint();
            }
        });
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public RegisterPage getRegisterPage() {
        return registerPage;
    }

    public void setRegisterPage(RegisterPage registerPage) {
        this.registerPage = registerPage;
    }
}
