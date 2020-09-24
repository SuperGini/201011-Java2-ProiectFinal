package client.gui.label.pages;

import client.gui.panel.TransparentPanel;
import lib.dto.user.UserDto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AccountPage extends JLabel {

    private JPanel transparentPanel;
    private JLabel credentialLabel;
    private JTextField changeCredentials;
    private JPasswordField newPassword;
    private JPasswordField oldPasswod;
    private JButton usernameButton;
    private JButton emailButton;
    private JButton phone1Button;
    private JButton phone2Button;
    private JButton changePasswordButton;

    private JLabel userLabel;
    private JLabel emailLabel;
    private JLabel phone1Label;
    private JLabel phone2Label;
    private JLabel categoryLabel;

    private UserDto userDto = null;

    private Color color = new Color(173, 53, 22, 255);
    private String change = "Change";
    private String [] genericLabel = {"user:", "email:", "phone 1:", "phone 2:", "category:", "password:     **********"};
    private List<JLabel> genericLabels = new ArrayList<>();
    private List<String> userfields = new ArrayList<>();


    public AccountPage(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        initTransparentPanel();
        initUsernameButton();
        initEmailButton();
        initPhone1Button();
        initPhoneNumber2();
        changePasswordButton();
        initChangeCredentialField();
        initOldPAssword();
        initNewPassword();
        initGenericalLabels();
        initUserLabels();


    }


    private void initTransparentPanel(){
        transparentPanel = new TransparentPanel(250,125,950,550);
        this.add(transparentPanel);



    }


    private void initUsernameButton(){
        usernameButton = new JButton(change);
        usernameButton.setBounds(30,100, 200, 30);
        usernameButton.setBackground(color);
        usernameButton.setFocusable(false);
        transparentPanel.add(usernameButton);

    }

    private void initEmailButton(){
        emailButton = new JButton(change);
        emailButton.setBounds(30,150, 200, 30);
        emailButton.setBackground(color);
        emailButton.setFocusable(false);
        transparentPanel.add(emailButton);
    }

    private void initPhone1Button(){
        phone1Button = new JButton(change);
        phone1Button.setBounds(30,200, 200, 30);
        phone1Button.setBackground(color);
        phone1Button.setFocusable(false);
        transparentPanel.add(phone1Button);
    }

    private void initPhoneNumber2(){
        phone2Button = new JButton(change);
        phone2Button.setBounds(30,250, 200, 30);
        phone2Button.setBackground(color);
        phone2Button.setFocusable(false);
        transparentPanel.add(phone2Button);
    }

    private void changePasswordButton(){
        changePasswordButton = new JButton(change);
        changePasswordButton.setBounds(30,300, 200, 30);
        changePasswordButton.setBackground(color);
        changePasswordButton.setFocusable(false);
        transparentPanel.add(changePasswordButton);
    }

    private void initChangeCredentialField(){
        changeCredentials  = new JTextField();
        changeCredentials.setBounds(250,100, 200, 30);
        transparentPanel.add(changeCredentials);
    }

    private void initOldPAssword(){
        oldPasswod = new JPasswordField();
        oldPasswod.setBounds(250,300, 200, 30);
        transparentPanel.add(oldPasswod);
    }



    private void initNewPassword(){
        newPassword = new JPasswordField();
        newPassword.setBounds(250,350, 200, 30);
        newPassword.setVisible(true);
        transparentPanel.add(newPassword);
    }

    private void initGenericalLabels(){
        for(int i = 0 ; i < 6; i++){
            credentialLabel = new JLabel(genericLabel[i]);
            credentialLabel.setBounds(600,100 + (i*50), 200, 30);
            genericLabels.add(credentialLabel);
            transparentPanel.add(credentialLabel);


        }
    }

    private void initUserLabels(){
         userLabel = new JLabel("");
         userLabel.setBounds( 660, 100, 200, 30);
         transparentPanel.add(userLabel);

         emailLabel = new JLabel("");
         emailLabel.setBounds(660, 150, 200, 30);
         transparentPanel.add(emailLabel);

         phone1Label = new JLabel("");
         phone1Label.setBounds(660, 200, 200, 30);
         transparentPanel.add(phone1Label);

         phone2Label = new JLabel("");
         phone2Label.setBounds(660, 250, 200, 30);
         transparentPanel.add(phone2Label);


         categoryLabel = new JLabel("");
         categoryLabel.setBounds(660, 300, 200, 30);
         transparentPanel.add(categoryLabel);


    }

    private void addUserFields(){
        userfields.add(userDto.getUserId().getUserName());
        userfields.add(userDto.getUserId().getEmailAdress());
        userfields.addAll(new ArrayList<>(userDto.getPhoneNumber()));
        userfields.add(userDto.getCategory().toString());

    }


    public JLabel getUserLabel() {
        return userLabel;
    }

    public void setUserLabel(JLabel userLabel) {
        this.userLabel = userLabel;
    }

    public JLabel getEmailLabel() {
        return emailLabel;
    }

    public void setEmailLabel(JLabel emailLabel) {
        this.emailLabel = emailLabel;
    }

    public JLabel getPhone1Label() {
        return phone1Label;
    }

    public void setPhone1Label(JLabel phone1Label) {
        this.phone1Label = phone1Label;
    }

    public JLabel getPhone2Label() {
        return phone2Label;
    }

    public void setPhone2Label(JLabel phone2Label) {
        this.phone2Label = phone2Label;
    }

    public JLabel getCategoryLabel() {
        return categoryLabel;
    }

    public void setCategoryLabel(JLabel categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
