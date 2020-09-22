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
import java.util.*;
import java.util.List;

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
    private JButton findClientButton;
    private JLabel clientLabel;
    private JLabel vehicleLabel;

    private JRadioButton clientRadioButton;
    private ButtonGroup buttonGroup;


    private PersonDto personDto;
    private CompanyDto companyDto;



        //:todo de facut cerintele pe fileduri la sfarsit -> ca sa nu fie fileduri goale cand persist auto/client

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
        initFindClientButton();

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

                AdressDto adressDto = new AdressDto(
                        streetField.getText(),
                        numberField.getText()
                );

                VehicleDto vehicleDto = new VehicleDto();
                vehicleDto.setSerialNumber(serialNumberField.getText());
                vehicleDto.setVehicleName(brandField.getText());



                if(button.isSelected() && button.getActionCommand().equals(clientType[0])){


                    PersonDto personDto = new PersonDto.Builder()
                                        .setAdresaDto(adressDto)
                                        .setCnpDto(cuiAndCnpField.getText())
                                        .setNameDto(nameField.getText())
                                        .build();
                    //setez masina pe persoana -> ca s asocieze id-ul in baza de date
                    personDto.setVehicleDtos(Set.of(vehicleDto));

                   if(!PersonController.getInstance().createPerson(personDto)){

                       JOptionPane.showMessageDialog(null, "Person created!");
                   }else{
                       JOptionPane.showMessageDialog(null, "Person is allready created!");
                   }
                }

                if(button.isSelected() && button.getActionCommand().equals(clientType[1])){
                    CompanyDto companyDto = new CompanyDto.Builder()
                                            .setAdressDto(adressDto)
                                            .setCuiDto(cuiAndCnpField.getText())
                                            .setNameDto(nameField.getText())
                                            .build();
                    //setez masina pe companie -> ca sa asocieze id-ul ibaza de date
                    companyDto.setVehicleDtos(Set.of(vehicleDto));

                    if(!CompanyController.getInstance().ceateCompany(companyDto)){
                        JOptionPane.showMessageDialog(null, "Company created!");

                    }else{
                        JOptionPane.showMessageDialog(null, "Company is allready created");
                    }
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

        for(int i = 0; i < 2; i++) {
            vehicleLabel = new JLabel(vehicleString[i]);
            vehicleLabel.setBounds(525, 250 + (i * 50), 100, 30);

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

            try {

                VehicleDto vehicleDto = new VehicleDto();
                vehicleDto.setVehicleName(brandField.getText());
                vehicleDto.setSerialNumber(serialNumberField.getText());
                Set<VehicleDto> vehicleDtos = new HashSet<>();
                vehicleDtos.add(vehicleDto);


                for (JRadioButton button : radioButtons) {
                    if (button.isSelected() && button.getActionCommand().equals(clientType[0])) {
                        Optional.ofNullable(personDto)
                                .ifPresentOrElse(s->s.setVehicleDtos(vehicleDtos),
                                        this::task);
                        vehicleDto.setClientDto(personDto);
                    }
                }

                for (JRadioButton button : radioButtons) {
                    if (button.isSelected() && button.getActionCommand().equals(clientType[1])) {
                        Optional.ofNullable(companyDto)
                                .ifPresentOrElse(s->s.setVehicleDtos(vehicleDtos),
                                        this::task);
                        vehicleDto.setClientDto(companyDto);
                    }
                }


               if(!VehicleController.getInstance().createVehicle(vehicleDto)){
                   JOptionPane.showMessageDialog(null, "Vehicle added to client");
               }

            }catch(IllegalArgumentException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Serial number is allready in the database");
            }
            catch(NullPointerException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Find the client first in the database then create the vehicle");

            }

        });
    }

    public void task(){
        throw new NullPointerException();
    }

    private void initFindClientButton(){
        findClientButton = new JButton("Find Client");
        findClientButton.setBounds(350,500,395,30);
        findClientButton.setBackground(Color.CYAN);
        transparentPanel.add(findClientButton);

        findClientButton.addActionListener(ev ->{

            if(!radioButtons.get(0).isSelected() && !radioButtons.get(1).isSelected() ){
                JOptionPane.showMessageDialog(null, "Plese select Person or Company to find by");
                return;
            }

            for(JRadioButton button : radioButtons){

                if(button.isSelected() && button.getActionCommand().equals(clientType[0])){

                    try{

                        personDto = PersonController.getInstance().findPersonByName(nameField.getText());

                        nameField.setText(personDto.getName());
                        cuiAndCnpField.setText(personDto.getCnp());
                        streetField.setText(personDto.getAdress().getStreet());
                        numberField.setText(personDto.getAdress().getNumber());
                        System.out.println(personDto.getId());

                    }catch(NoSuchElementException e){
                        JOptionPane.showMessageDialog(null, "Plese select Company to find");
                        e.printStackTrace();

                    }

                }

                if(button.isSelected() && button.getActionCommand().equals(clientType[1])) {

                    try{
                        companyDto = CompanyController.getInstance().findCompanyByName(nameField.getText());

                        nameField.setText(companyDto.getName());
                        cuiAndCnpField.setText(companyDto.getCui());
                        streetField.setText(companyDto.getAdress().getStreet());
                        numberField.setText(companyDto.getAdress().getNumber());
                        System.out.println(companyDto.getId());

                    }catch(NoSuchElementException e){
                        JOptionPane.showMessageDialog(null, "Plese select Person to find");
                        e.printStackTrace();

                    }
                }
            }
        });
    }
}
