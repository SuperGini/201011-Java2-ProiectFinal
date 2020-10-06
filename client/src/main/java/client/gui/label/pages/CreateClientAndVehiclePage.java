package client.gui.label.pages;

import client.controller.autovehicle.VehicleController;
import client.controller.client.CompanyController;
import client.controller.client.PersonController;
import client.gui.button.ZeeButton;
import client.gui.panel.TransparentPanel;
import lib.dto.autovehicle.VehicleDto;
import lib.dto.client.AdressDto;
import lib.dto.client.CompanyDto;
import lib.dto.client.PersonDto;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

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


    private String [] clientType = {"Person", "Company"};
    private String [] clientString = {"Name:", "Cui/cnp:", "Adress:", "Street:", "Number:"};
    private String [] vehicleString = {"Brand: ", "Serial number: "};

    private List<JLabel> clientLabels = new ArrayList<>();
    private List<JLabel> vehicleLabels = new ArrayList<>();
    private List<JRadioButton> radioButtons = new ArrayList<>();
    private Color colorOrange = new Color(167,32,7);


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
        nameField.setBorder(BorderFactory.createLineBorder(colorOrange));
        transparentPanel.add(nameField);
    }

    private void initCuiAndCnpField(){
        cuiAndCnpField = new JTextField();
        cuiAndCnpField.setBounds(125, 200, 300, 30);
        cuiAndCnpField.setBorder(BorderFactory.createLineBorder(colorOrange));
        transparentPanel.add(cuiAndCnpField);
    }


    private void initStreetField(){
        streetField = new JTextField();
        streetField.setBounds(125,300,300,30);
        streetField.setBorder(BorderFactory.createLineBorder(colorOrange));
        transparentPanel.add(streetField);
    }

    private void initNumberField(){
        numberField = new JTextField();
        numberField.setBounds(125,350,300,30);
        numberField.setBorder(BorderFactory.createLineBorder(colorOrange));
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
            clientRadioButton.setBounds(200 + (i*80),390,80,50);
            clientRadioButton.setOpaque(false);

            buttonGroup.add(clientRadioButton);
            radioButtons.add(clientRadioButton);
            transparentPanel.add(clientRadioButton);
        }
    }

    private void initCreateClientButton(){
        createClientButton = new ZeeButton(30,450,395,30,"Create client");
        transparentPanel.add(createClientButton);
    }

    //init Vehicle side
    private void initBrandField(){
        brandField = new JTextField();
        brandField.setBounds(620,250,300,30);
        brandField.setBorder(BorderFactory.createLineBorder(colorOrange));
        transparentPanel.add(brandField);
    }

    private void initSerialNumberField(){
        serialNumberField = new JTextField();
        serialNumberField.setBounds(620,300,300,30);
        serialNumberField.setBorder(BorderFactory.createLineBorder(colorOrange));
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
        createVehicleButton = new ZeeButton(525,450,395,30, "Create Vehicle");
       // createVehicleButton.setBounds(525,450,395,30);
        transparentPanel.add(createVehicleButton);
     //   createVehicleButton.addMouseListener(new MouseAdapterButton(createVehicleButton));

    }


    private void initFindClientButton(){
        findClientButton = new ZeeButton(277,500,395,30,"Find Client");
        transparentPanel.add(findClientButton);
    }

    public void findClient(){

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
                    companyDto = null;

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
                    personDto = null;

                }catch(NoSuchElementException e){
                    JOptionPane.showMessageDialog(null, "Plese select Person to find");
                    e.printStackTrace();

                }
            }
        }
    }

    public void createClient(){

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
                //setez masina pe companie -> ca sa asocieze id-ul in baza de date
                companyDto.setVehicleDtos(Set.of(vehicleDto));

                if(!CompanyController.getInstance().ceateCompany(companyDto)){
                    JOptionPane.showMessageDialog(null, "Company created!");

                }else{
                    JOptionPane.showMessageDialog(null, "Company is allready created");
                }
            }
        }
    }

    public void createVehicle(){

        if(validCarFields()){
            try {

                VehicleDto vehicleDto = new VehicleDto();
                vehicleDto.setVehicleName(brandField.getText());
                vehicleDto.setSerialNumber(serialNumberField.getText());
                Set<VehicleDto> vehicleDtos = new HashSet<>();
                vehicleDtos.add(vehicleDto);

//
                if (personDto != null && radioButtons.get(1).isSelected() && !personDto.getName().equals(nameField.getToolTipText())) {
                    JOptionPane.showMessageDialog(null, "Select Person");
                    return;
                }
                if (companyDto != null && radioButtons.get(0).isSelected() && !companyDto.getName().equals(nameField.getText())) {
                    JOptionPane.showMessageDialog(null, "Select Company");
                    return;
                }

                //lina de mai jos rezolva un bug: daca dau find dupa client si inainte sa bag masina in baza de date daca selectez Company
                // imi baga masina in baza de date fara id client
                if (radioButtons.get(0).isSelected() && nameField.getText().equals(personDto.getName())) {
                    Optional.of(personDto)
                            .ifPresentOrElse(s -> s.setVehicleDtos(vehicleDtos), NullPointerException::new);
                    vehicleDto.setClientDto(personDto);
                    if(createVehicle(vehicleDto)){
                        vehicleDtos.clear();
                        resetFields();
                        personDto = null;
                    }
                }


                //lina de mai jos rezolva un bug: daca dau find dupa client si inainte sa bag masina in baza de date daca selectez Person
                // imi baga masina in baza de date fara id client
                if (radioButtons.get(1).isSelected() && nameField.getText().equals(companyDto.getName())) {
                    Optional.ofNullable(companyDto)
                            .ifPresent(s -> s.setVehicleDtos(vehicleDtos));
                    vehicleDto.setClientDto(companyDto);

                    if(createVehicle(vehicleDto)){
                        vehicleDtos.clear();
                        resetFields();
                        companyDto = null;
                    }
                }
//
            }catch(NullPointerException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Find the client first in the database then create the vehicle");
            }
        }
    }

    private boolean createVehicle(VehicleDto vehicleDto){

        try {

            if(!VehicleController.getInstance().createVehicle(vehicleDto)){
                JOptionPane.showMessageDialog(null, "Vehicle added to client");
                return true;
            }

        }catch(IllegalArgumentException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Serial number is allready in the database");
        } catch(NullPointerException e){
            e.printStackTrace();


        }
        return false;
    }


    private boolean validClientFields(){

        if(nameField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter client name");
            return false;
        }

        if(cuiAndCnpField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter cui or cnp");
            return false;
        }

        if(streetField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter street name");
            return false;
        }

        if(numberField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter the number of the street");
            return false;
        }

        if(!radioButtons.get(0).isSelected() && !radioButtons.get(1).isSelected()){
            JOptionPane.showMessageDialog(null, "Select Person or Company");
            return false;
        }
        return true;
    }

    private boolean validCarFields(){

        if(validClientFields()){

            if(brandField.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Enter brand name");
                return false;
            }

            if(serialNumberField.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Enter serial number / VIN");
                return false;
            }
        }
        return true;
    }

    private void resetFields(){
          nameField.setText("");
          streetField.setText("");
          numberField.setText("");
          brandField.setText("");
          serialNumberField.setText("");
          cuiAndCnpField.setText("");
    }

    public JButton getCreateClientButton() {
        return createClientButton;
    }

    public JButton getCreateVehicleButton() {
        return createVehicleButton;
    }

    public JButton getFindClientButton() {
        return findClientButton;
    }
}
