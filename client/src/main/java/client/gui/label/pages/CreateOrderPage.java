package client.gui.label.pages;

import client.controller.autovehicle.ServiceOrderController;
import client.controller.autovehicle.VehicleController;
import client.gui.button.ZeeButton;
import client.gui.frame.MainFrame;
import client.gui.panel.TransparentPanel;
import client.util.mouseAdaptors.MouseAdapterButton;
import client.util.tables.Tables;
import lib.dto.autovehicle.ServiceOrderDto;
import lib.dto.autovehicle.Status;
import lib.dto.autovehicle.VehicleDto;
import lib.dto.bill.BillDto;
import lib.dto.bill.TotalPriceDto;
import lib.dto.client.ClientDto;
import lib.dto.user.Category;
import lib.dto.user.UserDto;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateOrderPage extends JLabel {

    private JPanel transparentPanel;
    private JTextArea carProblemArea;
    private JTable orderId;
    private JTable partsTable;
    private JTextField addProblemField;
    private JButton addProblemButton;
    private JButton findCarButton;
    private JButton createOrderButton;
    private JButton billButton;
    private JButton refreshOrdersButton;
    private JLabel genericLabel;
    private JLabel carProblemLabel;
    private JLabel orderPartsLable;
    private JButton getSelectedORderButton;
    private JTextField findField;
    private int lineProblemCount = 0;
    private JLabel orderLabel, userLabel, clientLabel, brandLabel, serialLabel;

    private List<JLabel> genericLabels = new ArrayList<>();

    private ClientDto clientDto = new ClientDto();
    private VehicleDto vehicleDto = new VehicleDto();
    private ServiceOrderDto serviceOrderDto;
    private BillDto billDto = new BillDto();
    private Tables tables;


    public CreateOrderPage(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        initTransparentPanel();
        initCarProblemLabel();
        intiCarProblemArea();
        initAddProblemField();
        initAddProblemButton();
        initGenerilLabels();
        initOrderPartsLabel();
        initFindCarButton();
        initFindField();
        initCreateOrderButton();
        initMiniLabels();
        initBillButton();
        initRefreshListButton();
        initTables();
    }

    private void initTransparentPanel(){
        transparentPanel = new TransparentPanel(250,125,950,550);
        this.add(transparentPanel);
    }

    private void initTables(){
        tables = new Tables(5,50,115,350, transparentPanel, false);
    }

    private void initCarProblemLabel(){
        carProblemLabel = new JLabel("Problems of the car:");
        carProblemLabel.setBounds(195,10,150,30);
        transparentPanel.add(carProblemLabel);
    }

    private void initOrderPartsLabel(){
        orderPartsLable = new JLabel("Order parts:");
        orderPartsLable.setBounds(755,10,100,30);
        transparentPanel.add(orderPartsLable);
    }

    private void initGenerilLabels(){
        String [] label = {"Order:", "User:", "Client:", "Brand:", "Serial:"};

        for( int i = 0; i < 5; i++){
            genericLabel = new JLabel(label[i]);
            genericLabel.setBounds(420,50 + (i*30), 50,20);
            genericLabels.add(genericLabel);
            transparentPanel.add(genericLabel);
        }
    }

    private void initMiniLabels(){
        orderLabel = new JLabel("");
        orderLabel.setBounds(460,50, 50,20);
        transparentPanel.add(orderLabel);

        userLabel = new JLabel("");
        userLabel.setBounds(460,80, 50,20);
        transparentPanel.add(userLabel);

        clientLabel = new JLabel("");
        clientLabel.setBounds(460,110, 50,20);
        transparentPanel.add(clientLabel);

        brandLabel = new JLabel("");
        brandLabel.setBounds(460,140, 50,20);
        transparentPanel.add(brandLabel);

        serialLabel = new JLabel("");
        serialLabel.setBounds(460,170, 50,20);
        transparentPanel.add(serialLabel);
    }

    private void initFindField(){
        findField = new JTextField();
        findField.setBounds(430,300,200,30);
        findField.setBorder(BorderFactory.createLineBorder(MouseAdapterButton.getColorOrange()));
        transparentPanel.add(findField);

    }

    private void initFindCarButton(){
        findCarButton = new ZeeButton(430,350,200,30,"find car serial number");
        transparentPanel.add(findCarButton);
    }

    private void initCreateOrderButton(){
        createOrderButton = new ZeeButton(430,500,200,30,"create order");
        transparentPanel.add(createOrderButton);
    }

    private void initRefreshListButton(){
        refreshOrdersButton = new ZeeButton(5,470,115,50,"refresh");
        refreshOrdersButton.setFont(new Font("Dialog",Font.BOLD, 15));
        transparentPanel.add(refreshOrdersButton);

    }

    private void initAddProblemField(){
        addProblemField = new JTextField();
        addProblemField.setBounds(125,460,300,30);
        addProblemField.setBorder(BorderFactory.createLineBorder(MouseAdapterButton.getColorOrange()));
        transparentPanel.add(addProblemField);

    }

    private void initAddProblemButton(){
        addProblemButton = new ZeeButton(125,500,300,30,"add problem");
        transparentPanel.add(addProblemButton);
    }

    private void intiCarProblemArea(){

        carProblemArea = new JTextArea();
        carProblemArea.setBounds(125,50,290,350);
        carProblemArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(carProblemArea);
        scrollPane.setBounds(125,50,290,350);
        scrollPane.setBorder(BorderFactory.createLineBorder(MouseAdapterButton.getColorOrange()));
        transparentPanel.add(scrollPane);
    }

    public void initBillButton(){
        billButton = new ZeeButton(645,500,300,30,"Bill");
        transparentPanel.add(billButton);
    }

     public void makeBill(){

        String billNumber = String.valueOf(tables.getId());
        String path = "./client/src/main/resources/bill/" + billNumber + ".txt";

        billDto.setBrand(brandLabel.getText());
        billDto.setOrderId(orderLabel.getText());
        billDto.setClientName(clientLabel.getText());
        billDto.setSerialNumber(serialLabel.getText());

        TotalPriceDto totalPriceDto = new TotalPriceDto(String.valueOf(tables.getTotal()));

        int updateStatus = ServiceOrderController.getInstance().updateServiceOrderStatus(tables.getId(), Status.CLOSE);

        if(!Files.exists(Paths.get(path))){

            if(updateStatus > 0){
                ServiceOrderController.getInstance().makeBill(tables.getPartsDtos(), path, billDto, totalPriceDto);
                JOptionPane.showMessageDialog(null, "Bill created");

                refreshOrderTable2();
            }

            if(updateStatus == 0){
                JOptionPane.showMessageDialog(null, "Bill was not created");
            }

        }else{
            JOptionPane.showMessageDialog(null, "Bill allready exists");
        }
    }


    public void createOrder(){

        UserDto userDto = MainFrame.getInstance().getAccountPage().getUserDto();

        if(userDto.getCategory().equals(Category.WAREHOUSE)){
            JOptionPane.showMessageDialog(null, "You dont have permision to create orders \n" +
                    " must have " + Category.BODY + " or " + Category.MECHANICAL + " privileges");
            return;
        }

        if(orderLabel.getText().equals("") && clientLabel.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Find a serial number before creating an order");
        }

        if(!orderLabel.getText().equals("") && !clientLabel.getText().equals("")) {
            int option = JOptionPane.showConfirmDialog(
                    null,"Create new Order?","confirm box",JOptionPane.YES_NO_OPTION);

            if(option == JOptionPane.YES_OPTION){
                resetFields();
            }
        }


        if(!orderLabel.getText().equals("") && !clientLabel.getText().equals("")){

            int option = JOptionPane.showConfirmDialog(
                    null,"Create new Order?","confirm box",JOptionPane.YES_NO_OPTION);

            if(option == JOptionPane.YES_OPTION){
                resetFields();
            }
        }

        if(orderLabel.getText().equals("") && !clientLabel.getText().equals("")){
            createNewOrder();
            resetFields();
        }
    }

    private void createNewOrder(){

        UserDto userDto = MainFrame.getInstance().getAccountPage().getUserDto();

        String text = carProblemArea.getText();
        String [] textLines = text.split("\n");
        List<String> lines = Arrays.asList(textLines);

        serviceOrderDto = new ServiceOrderDto.Builder()
                            .setCarProblems(lines)
                            .setVehicle(vehicleDto)
                            .setStatus(Status.OPEN)
                            .setClient(clientDto)
                            .setUser(userDto)
                            .build();

                if(!ServiceOrderController.getInstance().createServiceOrder(serviceOrderDto)){
                    JOptionPane.showMessageDialog(null, "Order created");
                    lineProblemCount = 0;

                    SwingUtilities.invokeLater(() ->{

                        refreshOrderTable();
                        tables.sendNotification();

                    });

                }else{
                    JOptionPane.showMessageDialog(null,"Order was not created");

                }
    }

    //method 1
    private void refreshOrderTable(){

       var newOrderIds = ServiceOrderController.getInstance().findAllServiceOrderIdAndStatus();
        tables.refreshOrderIdTable(newOrderIds);
        MainFrame.getInstance().getPartPage().getTables1().refreshOrderIdTable(newOrderIds);
    }

    //method 2
    public void refreshOrderTable2(){
        SwingUtilities.invokeLater(this::refreshOrderTable);
    }

    public void setGenericLabels( ServiceOrderDto serviceOrderDto){
        orderLabel.setText(String.valueOf(serviceOrderDto.getId()));
        clientLabel.setText(serviceOrderDto.getClientDto().getName());
        brandLabel.setText(serviceOrderDto.getVehicleDtos().getVehicleName());
        serialLabel.setText(serviceOrderDto.getVehicleDtos().getSerialNumber());
        userLabel.setText(serviceOrderDto.getUserDto().getUserId().getUserName());
    }

    public void addCarProblems(){

        if(orderLabel.getText().equals("")){
            lineProblemCount++;
            carProblemArea.append("\n" + lineProblemCount + "." + addProblemField.getText());
            addProblemField.setText("");
        }else{
            JOptionPane.showMessageDialog(null, "You can add car problems, only when \n" +
                                                "you create a new order");
        }
    }

    public void findCar(){
        List<Object []> object = VehicleController.getInstance().findVehicleWithClient(findField.getText());

        for(Object [] obj : object){

            int vehicleId = (int) obj[0];
            String brand = (String) obj[1];
            String serialNumber = (String) obj[2];
            int clientId = (int) obj[3];
            String clientName = (String) obj[4];

            brandLabel.setText(brand);
            serialLabel.setText(serialNumber);
            clientLabel.setText(clientName);
            vehicleDto.setId(vehicleId);
            clientDto.setId(clientId);
        }
    }

    private void resetFields(){
        orderLabel.setText("");
        clientLabel.setText("");
        brandLabel.setText("");
        serialLabel.setText("");
        findField.setText("");
        tables.getPartsDtos().clear();
        tables.tableDataParts();
        addProblemField.setText("");
        carProblemArea.setText("");
    }

    public JLabel getOrderLabel() {
        return orderLabel;
    }

    public void setOrderLabel(JLabel orderLabel) {
        this.orderLabel = orderLabel;
    }

    public JLabel getUserLabel() {
        return userLabel;
    }

    public void setUserLabel(JLabel userLabel) {
        this.userLabel = userLabel;
    }

    public JLabel getClientLabel() {
        return clientLabel;
    }

    public void setClientLabel(JLabel clientLabel) {
        this.clientLabel = clientLabel;
    }

    public JLabel getBrandLabel() {
        return brandLabel;
    }

    public void setBrandLabel(JLabel brandLabel) {
        this.brandLabel = brandLabel;
    }

    public JLabel getSerialLabel() {
        return serialLabel;
    }

    public void setSerialLabel(JLabel serialLabel) {
        this.serialLabel = serialLabel;
    }

    public JButton getAddProblemButton() {
        return addProblemButton;
    }

    public void setAddProblemButton(JButton addProblemButton) {
        this.addProblemButton = addProblemButton;
    }

    public JButton getGetSelectedORderButton() {
        return getSelectedORderButton;
    }

    public void setGetSelectedORderButton(JButton getSelectedORderButton) {
        this.getSelectedORderButton = getSelectedORderButton;
    }

    public JButton getFindCarButton() {
        return findCarButton;
    }

    public void setFindCarButton(JButton findCarButton) {
        this.findCarButton = findCarButton;
    }

    public JButton getCreateOrderButton() {
        return createOrderButton;
    }

    public JButton getBillButton() {
        return billButton;
    }

    public void setBillButton(JButton billButton) {
        this.billButton = billButton;
    }

    public void setCreateOrderButton(JButton createOrderButton) {
        this.createOrderButton = createOrderButton;
    }

    public JButton getRefreshOrdersButton() {
        return refreshOrdersButton;
    }

    public JTextArea getCarProblemArea() {
        return carProblemArea;
    }

    public void setCarProblemArea(JTextArea carProblemArea) {
        this.carProblemArea = carProblemArea;
    }

    public Tables getTables() {
        return tables;
    }

    public void setTables(Tables tables) {
        this.tables = tables;
    }
}
