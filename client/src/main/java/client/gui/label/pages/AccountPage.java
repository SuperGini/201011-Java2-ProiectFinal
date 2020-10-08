package client.gui.label.pages;

import client.controller.user.UserController;
import client.gui.button.ZeeButton;
import client.gui.panel.TransparentPanel;
import lib.dto.user.UserDto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AccountPage extends JLabel {

    private JPanel transparentPanel;
    private JLabel credentialLabel;
    private JTextField changeField;
    private JPasswordField newPasswordField;
    private JPasswordField verifyPasswodField;
    private JButton addPhone2;
    private JButton phone1Button;
    private JButton phone2Button;
    private JButton changePasswordButton;
    private JButton resetFields;

    private JLabel userLabel;
    private JLabel emailLabel;
    private JLabel phone1Label;
    private JLabel phone2Label;
    private JLabel categoryLabel;

    private UserDto userDto = null;

    private Color color = new Color(173, 53, 22, 255);
    private String change = "Change";
    private String [] genericLabel = {"user:", "email:", "phone 1:", "phone 2:", "password:     **********", "category:"};
    private List<JLabel> genericLabels = new ArrayList<>();
    private List<String> userfields = new ArrayList<>();

    Color colorOrange = new Color(167,32,7);


    public AccountPage(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        initTransparentPanel();
        initAddPhone2();
        initPhone1Button();
        initPhoneNumber2();
        changePasswordButton();
        initChangeField();
        initOldPAssword();
        initNewPassword();
        initGenericalLabels();
        initUserLabels();
        initResetFieldsButton();
    }


    private void initTransparentPanel(){
        transparentPanel = new TransparentPanel(250,125,950,550);
        this.add(transparentPanel);

    }

    private void initAddPhone2(){
        addPhone2 = new ZeeButton(30,150, 200, 30,"Add Phone");
        transparentPanel.add(addPhone2);


        addPhone2.addActionListener(ev ->{

            if(userDto.getPhoneNumber().size() < 2){

                if(!changeField.isVisible()){

                    changeField.setBounds(250,150, 200, 30);
                    changeField.setVisible(true);
                    return;
                }

                if(changeField.isVisible() && changeField.getY() != 150){
                    changeField.setBounds(250,150, 200, 30);
                    return;
                }
                

                if(changeField.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Enter phoneNumber");
                    return;
                }


                if(!userDto.getPhoneNumber().contains(changeField.getText())){

                    boolean addPhone = !UserController.getInstance().addPhoneNumber(userDto, changeField.getText());

                   if(addPhone){

                       JOptionPane.showMessageDialog(null, "Second phone number added");
                       userDto.getPhoneNumber().add(changeField.getText());
                       phone2Label.setText(changeField.getText());
                       changeField.setText("");
                       changeField.setVisible(false);
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
        phone1Button = new ZeeButton(30,200, 200, 30,change);
        transparentPanel.add(phone1Button);

        phone1Button.addActionListener(ev-> changePhoneNumber(phone1Label, phone1Button));

    }

    private void initPhoneNumber2(){
        phone2Button = new ZeeButton(30,250, 200, 30, change);
        transparentPanel.add(phone2Button);

        phone2Button.addActionListener(ev -> changePhoneNumber(phone2Label, phone2Button));
    }






    private void changePhoneNumber(JLabel label, JButton button){

        if(!changeField.isVisible()){

            if(button == phone2Button && changeField.getY() != 250){
                changeField.setBounds(250,250, 200, 30);

            }

            if(button == phone1Button && changeField.getY() != 200){
                changeField.setBounds(250,200, 200, 30);

            }

            changeField.setVisible(true);
            return;
        }

        if(changeField.getY() != button.getY()){
            changeField.setBounds(250,button.getY(), 200, 30);
            changeField.setText("");
            return;
        }

        try {

            if (!changeField.getText().equals("") && changeField.getY() == label.getY()) {

                List<String> phoneNumbers = userDto.getPhoneNumber();

                int index = phoneNumbers.indexOf(label.getText());
                if (!phoneNumbers.contains(changeField.getText())) {
                    phoneNumbers.set(index, changeField.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Duplicate phone number");
                    changeField.setText("");
                    return;
                }

                userDto.setPhoneNumber(phoneNumbers);

                if (!UserController.getInstance().updatePhoneNumber(userDto)) {
                    JOptionPane.showMessageDialog(null, "Phone number changed!");
                    label.setText(changeField.getText());
                    changeField.setText("");
                    changeField.setVisible(false);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Enter phone number!");
            }
        }catch(IndexOutOfBoundsException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null , "And second phone number first");
        }
    }

    private void changePasswordButton(){
        changePasswordButton = new ZeeButton(30,300, 200, 30, change);
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

                if(updatePassword > 0){

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

    private void initResetFieldsButton(){
        resetFields = new ZeeButton(30,350, 200, 30, "reset fields");
        transparentPanel.add(resetFields);
        resetFields.addActionListener(ev ->{
            resetPasswordFields();
            changeField.setText("");
            changeField.setVisible(false);
            newPasswordField.setVisible(false);
            verifyPasswodField.setVisible(false);
        });

    }

    private void resetPasswordFields(){
        newPasswordField.setText("");
        verifyPasswodField.setText("");
    }


    private void initChangeField(){
        changeField = new JTextField();
        changeField.setBounds(250,100, 200, 30);
        changeField.setVisible(false);
        changeField.setBorder(BorderFactory.createLineBorder(colorOrange));
        transparentPanel.add(changeField);
    }

    private void initOldPAssword(){
        verifyPasswodField = new JPasswordField();
        verifyPasswodField.setBounds(250,300, 200, 30);
        verifyPasswodField.setVisible(false);
        verifyPasswodField.setBorder(BorderFactory.createLineBorder(colorOrange));
        transparentPanel.add(verifyPasswodField);
    }



    private void initNewPassword(){
        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(250,350, 200, 30);
        newPasswordField.setVisible(false);
        newPasswordField.setBorder(BorderFactory.createLineBorder(colorOrange));
        transparentPanel.add(newPasswordField);
    }

    private void initGenericalLabels(){
        for(int i = 0 ; i < 6; i++){
            credentialLabel = new JLabel(genericLabel[i]);
            credentialLabel.setBounds(470,100 + (i*50), 200, 30);
            genericLabels.add(credentialLabel);
            transparentPanel.add(credentialLabel);


        }
    }

    private void initUserLabels(){
         userLabel = new JLabel("");
         userLabel.setBounds( 530, 100, 200, 30);
         transparentPanel.add(userLabel);

         emailLabel = new JLabel("");
         emailLabel.setBounds(530, 150, 200, 30);
         transparentPanel.add(emailLabel);

         phone1Label = new JLabel("");
         phone1Label.setBounds(530, 200, 200, 30);
         transparentPanel.add(phone1Label);

         phone2Label = new JLabel("");
         phone2Label.setBounds(530, 250, 200, 30);
         transparentPanel.add(phone2Label);


         categoryLabel = new JLabel("");
         categoryLabel.setBounds(530, 350, 200, 30);
         transparentPanel.add(categoryLabel);


    }

//    private void addUserFields(){
//        userfields.add(userDto.getUserId().getUserName());
//        userfields.add(userDto.getUserId().getEmailAdress());
//        userfields.addAll(new ArrayList<>(userDto.getPhoneNumber()));
//        userfields.add(userDto.getCategory().toString());
//
//    }




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
