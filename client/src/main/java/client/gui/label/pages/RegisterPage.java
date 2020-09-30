package client.gui.label.pages;

import client.controller.user.UserController;
import client.gui.panel.TransparentPanel;
import lib.dto.user.Category;
import lib.dto.user.UserDto;
import lib.dto.user.UserIdDto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RegisterPage extends JLabel {

    private TransparentPanel transparentPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel allFieldsLabel;
    private JTextField usernameField;
    private JTextField emailFiled;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField phoneNumberField;
    private JRadioButton radioButton;
    private JLabel radioButtonLabel;
    private ButtonGroup buttonGroup;




    private final String [] category = {"MECHANICAL", "BODY", "WAREHOUSE"};
    private final String [] fieldLabel = {"username", "email", "password", "confirm password", "phone number"};
    private List<JRadioButton> radioButtons;
    private List<JLabel> radioButtonLabels;
    private List<JLabel> fieldsLabels;

    public RegisterPage(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        initTransparentPanel();
        initUsernameField();
        initEmailField();
        initPasswordField();
        initConfirmPasswordField();
        initPhoneNumberField();
        initRadioButtons();
        initRadioButtonLabel();
        initRegisterButton();
        initLoginButton();
        initAllFieldsLabels();
    }

    private void initTransparentPanel(){
        transparentPanel = new TransparentPanel(400, 75, 400, 650);
        this.add(transparentPanel);
    }

    private void initUsernameField(){
        usernameField = new JTextField();
        usernameField.setBounds(50,95,300,30);
        transparentPanel.add(usernameField);
    }

    private void initEmailField(){
        emailFiled = new JTextField();
        emailFiled.setBounds(50,155,300,30);
        transparentPanel.add(emailFiled);
    }

    private void initPasswordField(){
        passwordField = new JPasswordField();
        passwordField.setBounds(50,215,300,30);
        transparentPanel.add(passwordField);
    }

    private void initConfirmPasswordField(){
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(50,275,300,30);
        transparentPanel.add(confirmPasswordField);
    }

    private void initPhoneNumberField(){
        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(50,335,300,30);
        transparentPanel.add(phoneNumberField);
    }

    private void initRadioButtons(){
        radioButtons = new ArrayList<>();
        buttonGroup = new ButtonGroup();

        for(int i = 0; i < 3; i++){
            radioButton = new JRadioButton();
            radioButton.setActionCommand(category[i]);
            radioButton.setBounds(200, 365 + i*30, 100, 50);
            radioButton.setOpaque(false);
            buttonGroup.add(radioButton);
            radioButtons.add(radioButton);
            transparentPanel.add(radioButton);
        }
    }

    private void initRadioButtonLabel(){
        radioButtonLabels = new ArrayList<>();

        for(int i = 0; i < 3; i++){
            radioButtonLabel = new JLabel(category[i]);
            radioButtonLabel.setBounds(100, 365 + i*30, 100, 50);
            radioButtonLabels.add(radioButtonLabel);
            transparentPanel.add(radioButtonLabel);
        }
    }

    private void initRegisterButton(){
        registerButton = new JButton("REGISTER");
        registerButton.setBounds(50, 475,300,30);
        transparentPanel.add(registerButton);
    }

    private void initLoginButton(){
        loginButton = new JButton("Go to login page!");
        loginButton.setBounds(50, 550, 300, 30);
        loginButton.setOpaque(false);
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setFocusable(false);
        loginButton.setFont(new Font("Dialog", Font.BOLD, 13));
        transparentPanel.add(loginButton);

    }

    private void initAllFieldsLabels(){
        fieldsLabels = new ArrayList<>();
        for(int i = 0; i < 5; i++ ){
            allFieldsLabel = new JLabel(fieldLabel[i]);
            allFieldsLabel.setBounds(50, 70 + i*60, 140, 30);
            fieldsLabels.add(allFieldsLabel);
            transparentPanel.add(allFieldsLabel);
        }
    }

    public boolean addUser(){
        List<String> phoneNumber = new ArrayList<>();
        UserDto userDto = new UserDto();
        UserIdDto userIdDto = new UserIdDto();

        phoneNumber.add(phoneNumberField.getText());

        userIdDto.setUserName(usernameField.getText());
        userIdDto.setEmailAdress(emailFiled.getText());

        userDto.setUserId(userIdDto);
        userDto.setPassword(new String(passwordField.getPassword()));
        userDto.setPhoneNumber(phoneNumber);
        userDto.setCategory(getCategory());

        return  UserController.getInstance().create(userDto);
    }


    private Category getCategory(){
        return radioButtons.stream()
                .filter(AbstractButton::isSelected)
                .map(e -> Category.valueOf(e.getActionCommand()))
                .findFirst()
                .orElseThrow();
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

    public JTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    public JTextField getEmailFiled() {
        return emailFiled;
    }

    public void setEmailFiled(JTextField emailFiled) {
        this.emailFiled = emailFiled;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JPasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public void setConfirmPasswordField(JPasswordField confirmPasswordField) {
        this.confirmPasswordField = confirmPasswordField;
    }

    public JTextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public void setPhoneNumberField(JTextField phoneNumberField) {
        this.phoneNumberField = phoneNumberField;
    }

    public JRadioButton getRadioButton() {
        return radioButton;
    }

    public void setRadioButton(JRadioButton radioButton) {
        this.radioButton = radioButton;
    }

    public List<JRadioButton> getRadioButtons() {
        return radioButtons;
    }

    public void setRadioButtons(List<JRadioButton> radioButtons) {
        this.radioButtons = radioButtons;
    }
}
