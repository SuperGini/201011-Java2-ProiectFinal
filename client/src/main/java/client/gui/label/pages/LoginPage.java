package client.gui.label.pages;

import client.controller.notification.NotificationController;
import client.controller.user.UserController;
import client.gui.button.ZeeButton;
import client.gui.frame.MainFrame;
import client.gui.panel.TransparentPanel;
import lib.dto.user.UserDto;

import javax.swing.*;
import java.awt.*;
import java.util.NoSuchElementException;
import java.util.Optional;

public class LoginPage extends JLabel {

    private TransparentPanel transparentPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;


    public LoginPage(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        initTransparentPanel();
        initLoginButton();
        initRegisterButton();
        initPasswordField();
        initUsernameField();
        initUsernameLabel();
        initPasswordLabel();

    }


    private void initTransparentPanel(){
        transparentPanel = new TransparentPanel(400, 75, 400, 650);
        this.add(transparentPanel);
    }

    private void initUsernameLabel(){
        usernameLabel = new JLabel("username / email");
        usernameLabel.setBounds(50,250,300,30);
        transparentPanel.add(usernameLabel);
    }

    private void initUsernameField(){
        usernameField = new JTextField();
        usernameField.setBounds(50,275,300,30);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.RED));
        transparentPanel.add(usernameField);
    }

    private void initPasswordLabel(){
        passwordLabel = new JLabel("password");
        passwordLabel.setBounds(50,310,300,30);
        transparentPanel.add(passwordLabel);

    }

    private void initPasswordField(){
        passwordField = new JPasswordField();
        passwordField.setBounds(50,335,300,30);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
        transparentPanel.add(passwordField);
    }

    private void initLoginButton(){
        loginButton = new ZeeButton(50, 395, 300, 30,"login");
        transparentPanel.add(loginButton);
    }

    private void initRegisterButton(){
        registerButton = new JButton("Go to register page!");
        registerButton.setBounds(50, 550, 300, 30);
        registerButton.setOpaque(false);
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setFocusable(false);

        registerButton.setFont(new Font("Dialog", Font.BOLD, 13));
        transparentPanel.add(registerButton);
    }

    public boolean validCredentials(){
        if(!usernameField.getText().equals("")){

                try{
                    if(usernameField.getText().contains("@")){ //cauta doar daca pe field e @ => email

                        UserDto userDto = UserController.getInstance()
                                .loginWithEmailAdress(usernameField.getText(), new String(passwordField.getPassword()));

                        if(Optional.ofNullable(userDto).isPresent()){

                            setUserAndLabelsInMyAccountPage(userDto);
                            NotificationController.getInstance().addUserToNotificationList(userDto);
                            MainFrame.getInstance().getLoginUserLabel().setText(userDto.getUserId().getUserName());

                            return true;
                        }
                    }


                    UserDto userDto = UserController.getInstance()
                            .loginWithUsername2(usernameField.getText(), new String(passwordField.getPassword()));

                    if(Optional.ofNullable(userDto).isPresent()){
                        setUserAndLabelsInMyAccountPage(userDto);
                        NotificationController.getInstance().addUserToNotificationList(userDto);
                        MainFrame.getInstance().getLoginUserLabel().setText(userDto.getUserId().getUserName());

                        return true;
                    }

                }catch(NoSuchElementException e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Wrong username or password");
                }
        }else{
            JOptionPane.showMessageDialog(null, "Enter credentials");
            return false;
        }

        return false;
    }


    private void setUserAndLabelsInMyAccountPage(UserDto userDto){

        AccountPage accoutPage = MainFrame.getInstance().getAccountPage();

        accoutPage.setUserDto(userDto);
        accoutPage.getUserLabel().setText(userDto.getUserId().getUserName());
        accoutPage.getEmailLabel().setText(userDto.getUserId().getEmailAdress());
        accoutPage.getPhone1Label().setText(userDto.getPhoneNumber().get(0));
        accoutPage.getCategoryLabel().setText(userDto.getCategory().toString());


        if(userDto.getPhoneNumber().size() == 2){
            accoutPage.getPhone2Label().setText(userDto.getPhoneNumber().get(1));
        }

        MainFrame.getInstance().getCreateOrderPage().getUserLabel().setText(userDto.getUserId().getUserName());

    }



    public TransparentPanel getTransparentPanel() {
        return transparentPanel;
    }

    public void setTransparentPanel(TransparentPanel transparentPanel) {
        this.transparentPanel = transparentPanel;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(JButton registerButton) {
        this.registerButton = registerButton;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }
}
