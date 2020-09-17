package client.gui.frame;

import AppPackage.AnimationClass;
import client.controller.PictureController;
import client.gui.label.LoginPage;
import client.gui.label.RegisterPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
