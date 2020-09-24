package client.gui.label.pages;

import client.controller.autovehicle.ServiceOrderController;
import client.controller.autovehicle.VehicleController;
import client.controller.client.ClientController;
import client.gui.frame.MainFrame;
import client.gui.panel.TransparentPanel;
import lib.dto.autovehicle.PartDto;
import lib.dto.autovehicle.ServiceOrderDto;
import lib.dto.autovehicle.VehicleDto;
import lib.dto.client.ClientDto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateOrderPage extends JLabel {

    private JPanel transparentPanel;
    private JList orderId;
    private JTextArea carProblemArea;
    private JTable partsArea;
    private JList partList;

    private JTable findPartArea;
    private JTextField addProblemField;
  //  private JTextField findPartField;
    private JButton addProblemButton;
    private JButton findPartButton;
    private JButton addPartToOrderButton;
    private JLabel genericLabel;
    private JLabel carProblemLabel;
    private JLabel orderPartsLable;
    private JButton findClientButton;
    private JButton findCarButton;
    private JButton createOrderButton;
    private JTextField findField;

    private JLabel orderLabel, userLabel, clientLabel, brandLabel, serialLabel;


    private DefaultListModel<ServiceOrderDto> listServiceOrderModel= new DefaultListModel<>();
    private DefaultListModel<PartDto> listPartModel = new DefaultListModel<>();
    private DefaultListModel<String> listProblemModel = new DefaultListModel<>();

    private JScrollPane scrollPane;

    private String [] label = {"Order:", "User:", "Client:", "Brand:", "Serial:"};
    private List<JLabel> genericLabels = new ArrayList<>();
  // private DefaultTableModel  tableModel;

    private ClientDto clientDto;
    private VehicleDto vehicleDto;


    public CreateOrderPage(int x, int y, int width, int height) {
     //   tableModel = new DefaultTableModel();
        this.setBounds(x, y, width, height);
        initTransparentPanel();
        initCarProblemLabel();
        initListServiceOrder();
        intiCarProblemArea();
        initAddProblemField();
        initAddProblemButton();
        //initPartsArea();
       // tableData();


        //initFindPartField();
        findPartButton();
        initAddPartToOrderButton();
        initfindPartsArea();
        initGenerilLabels();
        initORderParsLable();
        initFindClientButton();
        initFindCarButton();
        initFindField();
        initPartList();
        initCreateOrder();
        initMiniLabels();


    }


    private void initTransparentPanel(){
        transparentPanel = new TransparentPanel(250,125,950,550);
        this.add(transparentPanel);
    }

    private void initCarProblemLabel(){
        carProblemLabel = new JLabel("Problems of the car:");
        carProblemLabel.setBounds(195,10,150,30);
        transparentPanel.add(carProblemLabel);
    }

    private void initORderParsLable(){
        orderPartsLable = new JLabel("Order parts:");
        orderPartsLable.setBounds(755,10,100,30);
        transparentPanel.add(orderPartsLable);
    }

    private void initListServiceOrder(){
        orderId = new JList();
        orderId.setModel(listServiceOrderModel);
        orderId.setBounds(5,10,100,530);
        transparentPanel.add(orderId);

    }

    private void intiCarProblemArea(){

        carProblemArea = new JTextArea();
        carProblemArea.setBounds(115,50,300,400);

        JScrollPane scrollPane = new JScrollPane(carProblemArea);
        scrollPane.setBounds(115,50,300,400);
        transparentPanel.add(scrollPane);
    }

    private void initAddProblemField(){
        addProblemField = new JTextField();
        addProblemField.setBounds(115,460,300,30);
        transparentPanel.add(addProblemField);
    }

    private void initAddProblemButton(){
        addProblemButton = new JButton("add problem");
        addProblemButton.setBounds(115,500,300,30);
        transparentPanel.add(addProblemButton);

        addProblemButton.addActionListener(ev -> {
            carProblemArea.append(addProblemField.getText());



        });


    }

    private void initPartList(){
        partList = new JList(listPartModel);
        partList.setBounds(645,50,300,350);
        transparentPanel.add(partList);
    }





//    private void initPartsArea(){
//        partsArea = new JTable(tableModel);
//        scrollPane = new JScrollPane(partsArea);
//        partsArea.setBounds(645,50,300,350);
//        transparentPanel.add(partsArea);
//        partsArea.setRowHeight(20);
////        partsArea.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,15 ));
////        partsArea.setFont(new Font("Segoe UI", Font.BOLD, 12));
////        partsArea.getTableHeader().setOpaque(false);
////        partsArea.getTableHeader().setBackground(new Color(32,136,203));
////        partsArea.getTableHeader().setForeground(new Color(255,255,255));
////        partsArea.setShowVerticalLines(false);
////        partsArea.setSelectionBackground(new Color (232,57,95));





 //   }

        //todo: de vazut daca am timp sa fa cut tabel. Momentan am un bug la vizualizare si numerge
//    private void tableData(){
//        List<PartDto> partsDtos = new ArrayList<>();
//       // tableModel.setRowCount(0);
//
//        String [] columns = {"id", "part name", "count", "price"};
//
//
//        tableModel.setColumnIdentifiers(columns);
//
//
//
//
//        Object [] row = new Object [4];
//
//        for(PartDto partDto : partsDtos){
//            row[0] = partDto.getId();
//            row[1] = partDto.getPartName();
//            row[2] = partDto.getCount();
//            row[3] = partDto.getPrice();
//            tableModel.addRow(row);
//
//        }
//    }



    private void initfindPartsArea(){
        findPartArea = new JTable();
        findPartArea.setBounds(645,410,300,40);
        transparentPanel.add(findPartArea);
    }

//    private void initFindPartField(){
//        findPartField = new JTextField();
//        findPartField.setBounds(645,460,300,30);
//        transparentPanel.add(findPartField);
//    }



    private void initAddPartToOrderButton(){
        addPartToOrderButton =new JButton("add part to order");
        addPartToOrderButton.setBounds(645,500,300,30); //430,500,200,30
        transparentPanel.add(addPartToOrderButton);
    }

    private void initGenerilLabels(){
        for( int i = 0; i < 5; i++){
            genericLabel = new JLabel(label[i]);
            genericLabel.setBounds(420,50 + (i*30), 50,20);
            genericLabels.add(genericLabel);
            transparentPanel.add(genericLabel);
        }
    }

    private void initMiniLabels(){
        orderLabel = new JLabel("cxcvxcv");
        orderLabel.setBounds(460,50, 50,20);
        transparentPanel.add(orderLabel);

        userLabel = new JLabel("dasd");
        userLabel.setBounds(460,80, 50,20);
        transparentPanel.add(userLabel);

        clientLabel = new JLabel("ddd");
        clientLabel.setBounds(460,110, 50,20);
        transparentPanel.add(clientLabel);

        brandLabel = new JLabel("gdsffgsdf");
        brandLabel.setBounds(460,140, 50,20);
        transparentPanel.add(brandLabel);

        serialLabel = new JLabel("fsdfgsafsad");
        serialLabel.setBounds(460,170, 50,20);
        transparentPanel.add(serialLabel);
    }


    private void initFindField(){
        findField = new JTextField();
        findField.setBounds(430,340,200,30);
        transparentPanel.add(findField);
    }

    private void initFindClientButton(){
        findClientButton = new JButton("find client");
        findClientButton.setBounds(430,380,200,30);
        transparentPanel.add(findClientButton);

        findClientButton.addActionListener( ev -> {

            clientDto = ClientController.getInstance().findClientByName(findField.getText());
            clientLabel.setText(clientDto.getName());

        });




    }

    //todo: de aici pot sa scot si clientul dupa id de vazut cum fac asta ca sa nu mai fac un find de client.
    //todo: de vazut cum fac un obiect combinat
    private void initFindCarButton(){
        findCarButton = new JButton("find car serial");
        findCarButton.setBounds(430,420,200,30);
        transparentPanel.add(findCarButton);

        findCarButton.addActionListener( ev -> {
            vehicleDto = VehicleController.getInstance().findBySerialNumber(findField.getText());
            brandLabel.setText(vehicleDto.getVehicleName());
            serialLabel.setText(vehicleDto.getSerialNumber());



        });

    }

    private void findPartButton(){
        findPartButton = new JButton("find part");
        findPartButton.setBounds(430,460,200,30);
        transparentPanel.add(findPartButton);
    }

    private void initCreateOrder(){
        createOrderButton = new JButton("create order");
        createOrderButton.setBounds(430,500,200,30);
        transparentPanel.add(createOrderButton);

        createOrderButton.addActionListener(ev ->{

            ServiceOrderDto serviceOrderDto = new ServiceOrderDto();

            serviceOrderDto.setIdClient(clientDto.getId());
            serviceOrderDto.setIdVehicul(vehicleDto.getId());
            serviceOrderDto.setIdUsername(
                    MainFrame.getInstance().getAccountPage().getUserDto().getUserId().getUserName()
            );



            String text = carProblemArea.getText();
            String [] textLines = text.split("\n");
            List<String> lines = Arrays.asList(textLines);

            serviceOrderDto.setCarProblems(lines);

            ServiceOrderController.getInstance().createServiceOrder(serviceOrderDto);



        });


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

    public JButton getFindPartButton() {
        return findPartButton;
    }

    public void setFindPartButton(JButton findPartButton) {
        this.findPartButton = findPartButton;
    }

    public JButton getAddPartToOrderButton() {
        return addPartToOrderButton;
    }

    public void setAddPartToOrderButton(JButton addPartToOrderButton) {
        this.addPartToOrderButton = addPartToOrderButton;
    }

    public JButton getFindClientButton() {
        return findClientButton;
    }

    public void setFindClientButton(JButton findClientButton) {
        this.findClientButton = findClientButton;
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

    public void setCreateOrderButton(JButton createOrderButton) {
        this.createOrderButton = createOrderButton;
    }
}
