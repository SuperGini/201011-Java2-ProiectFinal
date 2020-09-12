package client.gui.label;

import client.gui.panel.TransparentPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class RegisterPage extends JLabel {

    private TransparentPanel transparentPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel allFieldsLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel emailLabel;
    private JLabel phoneNumberLabel;
    private JTextField usernameField;
    private JTextField emailFiled;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField phoneNumberField;
    private JRadioButton radioButton;
    private JLabel radioButtonLabel;
    private ButtonGroup buttonGroup;


    private final String [] category = {"mechanical", "body & paint", "warehouse"};
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
        transparentPanel = new TransparentPanel(400, 100, 400, 600);
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
        loginButton = new JButton("To login page");
        loginButton.setBounds(50, 550, 300, 30);
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




}
