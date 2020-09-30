package client.gui.frame;

import AppPackage.AnimationClass;
import client.controller.media.PictureController;
import client.gui.label.MovingLabel;
import client.gui.label.pages.*;
import client.gui.panel.HorizontalTransparentPanel;
import client.util.MouseAdapterLogAndRegister;
import client.util.SoundConvertor;
import client.util.SoundPlay;
import lib.dto.autovehicle.VehicleDto;
import lib.dto.client.ClientDto;

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
    private JLabel clientAndVehiclePage;
    private JLabel partPage;
    private AccountPage accountPage;
    private CreateOrderPage createOrderPage;
    private JLabel statisticPage;
    private MovingLabel upperLabel, lowerLabel;
    private LeftButtonPage leftButtonPage;
    private HorizontalTransparentPanel upperPanel, lowerPanel;

    private ScheduledExecutorService randomPicture = Executors.newSingleThreadScheduledExecutor();
    private static AnimationClass slideEfect = new AnimationClass();
    private static SoundPlay soundPlay = new SoundPlay();

    private List<JLabel> pages;

    private int poitX = 1800;

    private ClientDto clientDto;
    private VehicleDto vehicleDto;
    private SoundConvertor soundConvertor;


    private MainFrame(){
        soundConvertor = new SoundConvertor();
        initFrame();
        initBackgroundLabel();
        mouseListener();
        initAccountPage();
        initLoginPage();
        initRegisterPage();
        initLeftButtonPage();
        initCreateClientAndVehiclePage();
        initPartPage();

        initCreateOrderPage();
        initStatisticPage();
        getPages();
        moveLAbelLeft();
        initUpperLabelAndPanel();
        initLowerLabelAndPanel();


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


                moveLoginPageRaightAndLeftButtonPageRaight();
            }
        });

        loginPage.getRegisterButton().addActionListener(ev -> {
            moveLabesLeftAndRaight(registerPage);

        });

        loginPage.getRegisterButton().addMouseListener(new MouseAdapterLogAndRegister());
    }

    private void initRegisterPage(){
        registerPage = new RegisterPage(1200,0,1200,800);
        backgroundLabel.add(registerPage);

        registerPage.getRegisterButton().addActionListener(ev ->{
            if(!registerPage.addUser()){
                JOptionPane.showMessageDialog(null, "User created");
            }

        });

        registerPage.getLoginButton().addActionListener(ev -> {
            moveLabesLeftAndRaight(loginPage);
        });

        registerPage.getLoginButton().addMouseListener(new MouseAdapterLogAndRegister());



    }

    //method 1
    private void scheduleWithFixedDelay(){

        //:todo: nu uita sa dai shutdown la THREAD
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
        lowerPanel = new HorizontalTransparentPanel(0,0,1200,75, false);
        lowerLabel.add(lowerPanel);

    }

    private void initLeftButtonPage(){
        leftButtonPage = new LeftButtonPage(-200,75,200,650);
        backgroundLabel.add(leftButtonPage);

            //todo: de vazut daca pot sa le criu pe astea mai usor cu mai putin cod
            leftButtonPage.getButtons().get(0)
                    .addActionListener( ev ->movePagesLeftAndRaight(createOrderPage));

            leftButtonPage.getButtons().get(1)
                    .addActionListener( ev ->movePagesLeftAndRaight(clientAndVehiclePage));

            leftButtonPage.getButtons().get(2)
                    .addActionListener(ev -> movePagesLeftAndRaight(partPage));

            leftButtonPage.getButtons().get(3)
                    .addActionListener( ev -> movePagesLeftAndRaight(statisticPage));

            leftButtonPage.getButtons().get(4)
                    .addActionListener( ev -> movePagesLeftAndRaight(accountPage));

    }

    private void initCreateClientAndVehiclePage(){
        clientAndVehiclePage = new CreateClientAndVehiclePage(poitX,0,1200,800);
        backgroundLabel.add(clientAndVehiclePage);
    }

    private void initPartPage(){
        partPage = new PartPage(poitX,0,1200,800);
        backgroundLabel.add(partPage);
    }


    private void initAccountPage(){
        accountPage = new AccountPage(poitX,0,1200,800);
        backgroundLabel.add(accountPage);
    }

    private void initCreateOrderPage(){
        createOrderPage = new CreateOrderPage(poitX,0,1200,800);
        backgroundLabel.add(createOrderPage);
    }

    private void initStatisticPage(){
        statisticPage = new StatisticsPage(poitX,0,1200,800);
        backgroundLabel.add(statisticPage);
    }



    private List<JLabel> getPages(){
        pages = new ArrayList<>();
        pages.add(createOrderPage);
        pages.add(clientAndVehiclePage);
        pages.add(partPage);
        pages.add(statisticPage);
        pages.add(accountPage);
        pages.add(loginPage);
        pages.add(registerPage);
        return pages;
    }

    private void moveLabesLeftAndRaight(JLabel labelPage){
        for(JLabel page: pages){
            if((page.getX() == 400)  && (page != labelPage)){
                slideEfect.jLabelXRight(400,1200,1,4,page);
                slideEfect.jLabelXLeft(1200,400,1,4,labelPage);
            }
        }
    }

    private void movePagesLeftAndRaight(JLabel labelPage){



        for(JLabel page : pages){
            if(page.getX() == 0 && (page != labelPage)){
                slideEfect.jLabelXRight(0,poitX,1,6,page);
                slideEfect.jLabelXLeft(poitX,0,1,6,labelPage);
            }
        }
    }


    private void moveLoginPageRaightAndLeftButtonPageRaight(){
        slideEfect.jLabelXRight(-200,0,2, 4,leftButtonPage);
        slideEfect.jLabelXRight(400,1200,2,4, loginPage);
        slideEfect.jLabelXLeft(poitX,0,1,6,createOrderPage);
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
                upperPanel.setColor1(new Color(0,175,0,80));
                upperPanel.repaint();
                lowerPanel.setColor1(new Color(0,175,0,80));
                lowerPanel.repaint();

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                super.windowLostFocus(e);
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

    public JLabel getClientAndVehiclePage() {
        return clientAndVehiclePage;
    }

    public void setClientAndVehiclePage(JLabel clientAndVehiclePage) {
        this.clientAndVehiclePage = clientAndVehiclePage;
    }

    public JLabel getPartPage() {
        return partPage;
    }

    public void setPartPage(JLabel partPage) {
        this.partPage = partPage;
    }


    public AccountPage getAccountPage() {
        return accountPage;
    }

    public void setAccountPage(AccountPage accountPage) {
        this.accountPage = accountPage;
    }

    public CreateOrderPage getCreateOrderPage() {
        return createOrderPage;
    }

    public void setCreateOrderPage(CreateOrderPage createOrderPage) {
        this.createOrderPage = createOrderPage;
    }

    public JLabel getStatisticPage() {
        return statisticPage;
    }

    public void setStatisticPage(JLabel statisticPage) {
        this.statisticPage = statisticPage;
    }

    private static final class SingletonHolder{
        public static final MainFrame INSTANCE = new MainFrame();
    }

    public static MainFrame getInstance(){
        return SingletonHolder.INSTANCE;
    }

}
