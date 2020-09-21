package client.gui.label.pages;

import client.controller.autovehicle.VehicleController;
import client.controller.client.CompanyController;
import client.controller.client.PersonController;
import client.gui.panel.TransparentPanel;
import lib.dto.autovehicle.VehicleDto;
import lib.dto.client.AdressDto;
import lib.dto.client.CompanyDto;
import lib.dto.client.PersonDto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CreateClientAndVehiclePage extends JLabel {

    private JPanel transparentPanel;
    private JLabel client, vehicle;
    private JTextField nameField;
    private JTextField streetField;
    private JTextField numberField;
    private JTextField brandField;
    private JTextField serialNumberField;
    private JTextField cuiAndCnpField;
    private JButton createClientButton;
    private JButton createVehicleButton;
    private JLabel clientLabel;
    private JLabel vehicleLabel;

    private JRadioButton clientRadioButton;
    private ButtonGroup buttonGroup;




    private String [] clientType = {"Person", "Company"};
    private String [] clientString = {"Name:", "Cui/cnp:", "Adress:", "Street:", "Number:"};
    private String [] vehicleString = {"Brand: ", "Serial number: "};

    private List<JLabel> clientLabels = new ArrayList<>();
    private List<JLabel> vehicleLabels = new ArrayList<>();
    private List<JRadioButton> radioButtons = new ArrayList<>();


    public CreateClientAndVehiclePage(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        initTransparentPanel();
        initClientAndVehicleLabel();
        initNameField();
        initCuiAndCnpField();
        initStreetField();
        initNumberField();
        initCreateClientButton();
        initClientLabels();
        initRadioButtons();

        initBrandField();
        initSerialNumberField();
        createVehicleButton();
        initVehicleLabes();
    }

    private void initTransparentPanel(){
        transparentPanel = new TransparentPanel(250,125,950,550);
        this.add(transparentPanel);
    }

    private void initClientAndVehicleLabel(){
        client = new JLabel("Client");
        client.setFont(new Font("Dialog", Font.BOLD,17));
        client.setBounds(225,50,70,50);

        vehicle = new JLabel("Vehicle");
        vehicle.setFont(new Font("Dialog", Font.BOLD,17));
        vehicle.setBounds(700,50,70,50);

        transparentPanel.add(client);
        transparentPanel.add(vehicle);
    }

    //init Client side

    private void initNameField(){
        nameField = new JTextField();
        nameField.setBounds(125,150,300,30);
        transparentPanel.add(nameField);
    }

    private void initCuiAndCnpField(){
        cuiAndCnpField = new JTextField();
        cuiAndCnpField.setBounds(125, 200, 300, 30);
        transparentPanel.add(cuiAndCnpField);
    }


    private void initStreetField(){
        streetField = new JTextField();
        streetField.setBounds(125,250,300,30);
        transparentPanel.add(streetField);
    }

    private void initNumberField(){
        numberField = new JTextField();
        numberField.setBounds(125,300,300,30);
        transparentPanel.add(numberField);
    }


    private void initClientLabels(){

        for(int i = 0; i < 5; i++){
            clientLabel = new JLabel(clientString[i]);
            clientLabel.setBounds(30,150 + (i*50),100,30);
            clientLabels.add(clientLabel);
            transparentPanel.add(clientLabel);

        }
    }

    private void initRadioButtons(){

        buttonGroup = new ButtonGroup();

        for(int i = 0; i < 2; i++){
            clientRadioButton = new JRadioButton(clientType[i]);
            clientRadioButton.setActionCommand(clientType[i]);
            clientRadioButton.setBounds(200 + (i*80),350,80,50);
            clientRadioButton.setOpaque(false);

            buttonGroup.add(clientRadioButton);
            radioButtons.add(clientRadioButton);
            transparentPanel.add(clientRadioButton);
        }
    }

    private void initCreateClientButton(){
        createClientButton = new JButton("Create client");
        createClientButton.setBounds(30,450,395,30);
        createClientButton.setBackground(Color.CYAN);
        transparentPanel.add(createClientButton);

        createClientButton.addActionListener(ev -> {

            for(JRadioButton button : radioButtons){
                if(button.isSelected() && button.getActionCommand().equals(clientType[0])){
                    AdressDto adressDto = new AdressDto(
                            streetField.getText(),
                            numberField.getText()
                    );

                    PersonDto personDto = new PersonDto.Builder()
                                        .setAdresaDto(adressDto)
                                        .setCnpDto(cuiAndCnpField.getText())
                                        .setNameDto(nameField.getText())
                                        .build();


                    VehicleDto vehicleDto = new VehicleDto();
                               vehicleDto.setSerialNumber(serialNumberField.getText());
                               vehicleDto.setVehicleName(brandField.getText());

                               personDto.setVehicleDtos(Set.of(vehicleDto));



                   if(!PersonController.getInstance().createPerson(personDto)){
                       JOptionPane.showMessageDialog(null, "Person created!");
                   }else{
                       JOptionPane.showMessageDialog(null, "Person is allready created!");
                   }


                }

                if(button.isSelected() && button.getActionCommand().equals(clientType[1])){
                    CompanyDto companyDto = new CompanyDto();
                    companyDto.setName(nameField.getText());

                    CompanyController.getInstance().ceateCompany(companyDto);


                }

            }



        });
    }


    //init Vehicle side

    private void initBrandField(){
        brandField = new JTextField();
        brandField.setBounds(620,250,300,30);
        transparentPanel.add(brandField);
    }

    private void initSerialNumberField(){
        serialNumberField = new JTextField();
        serialNumberField.setBounds(620,300,300,30);
        transparentPanel.add(serialNumberField);
    }

    private void initVehicleLabes(){

        for(int i = 0; i < 2; i++){
            vehicleLabel = new JLabel(vehicleString[i]);
            vehicleLabel.setBounds(525,250 + (i*50), 100,30);

            vehicleLabels.add(vehicleLabel);
            transparentPanel.add(vehicleLabel);


        }

    }





    private void createVehicleButton(){
        createVehicleButton = new JButton("Create Vehicle");
        createVehicleButton.setBounds(525,450,395,30);
        createVehicleButton.setBackground(Color.CYAN);
        transparentPanel.add(createVehicleButton);

        createVehicleButton.addActionListener(ev -> {
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setSerialNumber(serialNumberField.getText());
            vehicleDto.setVehicleName(brandField.getText());

            VehicleController.getInstance().createVehicle(vehicleDto);




        });
    }




}
