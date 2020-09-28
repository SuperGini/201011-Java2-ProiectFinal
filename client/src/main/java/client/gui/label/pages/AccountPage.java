package client.gui.label.pages;

import client.controller.user.UserController;
import client.gui.panel.TransparentPanel;
import lib.dto.user.UserDto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AccountPage extends JLabel {

    private JPanel transparentPanel;
    private JLabel credentialLabel;
    private JTextField changeCredentialsField;
    private JPasswordField newPasswordField;
    private JPasswordField verifyPasswodField;
    private JButton usernameButton;
    private JButton addPhone2;
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
        initAddPhone2();
//        initUsernameButton();
//        initEmailButton();
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


//    private void initUsernameButton() {
//        usernameButton = new JButton(change);
//        usernameButton.setBounds(30, 100, 200, 30);
//        usernameButton.setBackground(color);
//        usernameButton.setFocusable(false);
//        transparentPanel.add(usernameButton);
//
//    }





    private void initAddPhone2(){
        addPhone2 = new JButton("Add Phone");
        addPhone2.setBounds(30,150, 200, 30);
        addPhone2.setBackground(color);
        addPhone2.setFocusable(false);
        transparentPanel.add(addPhone2);

        addPhone2.addActionListener(ev ->{

            if(userDto.getPhoneNumber().size() < 2){

                System.out.println(userDto.getPhoneNumber().toString());
                if(changeCredentialsField.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Enter phoneNumber");
                    return;
                }


                if(!userDto.getPhoneNumber().contains(changeCredentialsField.getText())){

                    boolean addPhone = !UserController.getInstance().addPhoneNumber(userDto, changeCredentialsField.getText());

                   if(addPhone){

                       JOptionPane.showMessageDialog(null, "Second phone number added");
                       userDto.getPhoneNumber().add(changeCredentialsField.getText());
                       phone2Label.setText(changeCredentialsField.getText());
                       changeCredentialsField.setText("");
                   }

                }else{
                    JOptionPane.showMessageDialog(null, "You allready have this phone number. Add another number");
                }


            }else{
                JOptionPane.showMessageDialog(null,"You can't add any phone numbers. Limit is 2 phone numbers");
            }


        });
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

        phone2Button.addActionListener(ev ->{

            changeCredentialsField.setVisible(true);


            if(!changeCredentialsField.getText().equals("")){



                List<String> phoneNumbers = userDto.getPhoneNumber();

                int index = phoneNumbers.indexOf(phone2Label.getText());
                phoneNumbers.set(index,changeCredentialsField.getText());

                userDto.setPhoneNumber(phoneNumbers);

                System.out.println(userDto.getPhoneNumber().toString());


                if(!UserController.getInstance().updatePhoneNumber(userDto)){
                    JOptionPane.showMessageDialog(null,"Phone number changed");
                    return;
                }

            }









           JOptionPane.showMessageDialog(null, "Error");



        });
    }

    private void changePasswordButton(){
        changePasswordButton = new JButton(change);
        changePasswordButton.setBounds(30,300, 200, 30);
        changePasswordButton.setBackground(color);
        changePasswordButton.setFocusable(false);
        transparentPanel.add(changePasswordButton);

        changePasswordButton.addActionListener(ev->{

            if(!newPasswordField.isVisible()){
                newPasswordField.setVisible(true);
                verifyPasswodField.setVisible(true);
                return;
            }


            if(String.valueOf(newPasswordField.getPassword()).equals("")){
                JOptionPane.showMessageDialog(null, "EnterPassword");
                return;
            }

            if(new String(newPasswordField.getPassword()).equals(new String(verifyPasswodField.getPassword()))){

                int updatePassword =  UserController.getInstance()
                        .updatePassword(new String(newPasswordField.getPassword()),userDto);

              //  userDto.setPassword(new String(newPasswordField.getPassword()));

              //  UserController.getInstance().updatePhoneNumber(userDto); -> merge dar face mult insert


                if(updatePassword > 0){

                    System.out.println(userDto.getPassword());



                    JOptionPane.showMessageDialog(null, "Password updated");
                    newPasswordField.setVisible(false);
                    verifyPasswodField.setVisible(false);
                    resetPasswordFields();
                }

                if(updatePassword == 0){
                    JOptionPane.showMessageDialog(null, "Password was not updated");
                    resetPasswordFields();
                }

            }else{
                JOptionPane.showMessageDialog(null, "Passwords do not match");
                resetPasswordFields();
            }

        });
    }

    private void resetPasswordFields(){
        newPasswordField.setText("");
        verifyPasswodField.setText("");
    }


    private void initChangeCredentialField(){
        changeCredentialsField = new JTextField();
        changeCredentialsField.setBounds(250,100, 200, 30);
        changeCredentialsField.setVisible(false);
        transparentPanel.add(changeCredentialsField);
    }

    private void initOldPAssword(){
        verifyPasswodField = new JPasswordField();
        verifyPasswodField.setBounds(250,300, 200, 30);
        verifyPasswodField.setVisible(false);
        transparentPanel.add(verifyPasswodField);
    }



    private void initNewPassword(){
        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(250,350, 200, 30);
        newPasswordField.setVisible(false);
        transparentPanel.add(newPasswordField);
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
