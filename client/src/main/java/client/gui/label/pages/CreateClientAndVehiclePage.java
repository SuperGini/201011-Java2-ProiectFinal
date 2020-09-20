package client.gui.label.pages;

import client.gui.panel.TransparentPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreateClientAndVehiclePage extends JLabel {

    private JPanel transparentPanel;
    private JLabel client, vehicle;
    private JTextField name;
    private JTextField street;
    private JTextField number;
    private JTextField brandField;
    private JTextField serialNumberField;
    private JButton createClientButton;
    private JButton createVehicleButton;
    private JLabel clientLabel;
    private JLabel vehicleLabel;
    private JRadioButton clientRadioButton;
    private ButtonGroup buttonGroup;





    private String [] clientString = {"Name:", "Adress:", "Street:", "Number:"};
    private String [] vehicleString = {"Brand: ", "Serial number: "};

    private List<JLabel> clientLabels = new ArrayList<>();
    private List<JLabel> vehicleLabels = new ArrayList<>();
    private List<JRadioButton> radioButtons = new ArrayList<>();


    public CreateClientAndVehiclePage(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        initTransparentPanel();
        initClientAndVehicleLabel();
        initNameField();
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
        name = new JTextField();
        name.setBounds(125,150,300,30);
        transparentPanel.add(name);


    }

    private void initStreetField(){
        street = new JTextField();
        street.setBounds(125,250,300,30);
        transparentPanel.add(street);
    }

    private void initNumberField(){
        number = new JTextField();
        number.setBounds(125,300,300,30);
        transparentPanel.add(number);
    }

    private void initCreateClientButton(){
        createClientButton = new JButton("Create client");
        createClientButton.setBounds(30,450,395,30);
        createClientButton.setBackground(Color.CYAN);
        transparentPanel.add(createClientButton);
    }

    private void initClientLabels(){

        for(int i = 0; i < 4; i++){
            clientLabel = new JLabel(clientString[i]);
            clientLabel.setBounds(30,150 + (i*50),100,30);
            clientLabels.add(clientLabel);
            transparentPanel.add(clientLabel);

        }
    }

    private void initRadioButtons(){
        String [] clientType = {"Person", "Company"};
        buttonGroup = new ButtonGroup();

        for(int i = 0; i < 2; i++){
            clientRadioButton = new JRadioButton(clientType[i]);
            clientRadioButton.setBounds(200 + (i*80),350,80,50);
            clientRadioButton.setOpaque(false);

            buttonGroup.add(clientRadioButton);
            radioButtons.add(clientRadioButton);
            transparentPanel.add(clientRadioButton);
        }
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
    }




}
