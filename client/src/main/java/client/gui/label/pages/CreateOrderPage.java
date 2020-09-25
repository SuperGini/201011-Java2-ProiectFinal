package client.gui.label.pages;

import client.controller.autovehicle.PartController;
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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CreateOrderPage extends JLabel {

    private JPanel transparentPanel;
    private JTable orderId;
    private JTextArea carProblemArea;
    private JTable partsArea;
  //  private JList partList;
    private JLabel countLabel;
    private JTextField countField;
    private JTextArea findPartArea;

  //  private JTable findPartArea;
    private JTextField addProblemField;
    private JTextField findPartField;
    private JButton addProblemButton;
    private JButton findPartButton;
    private JButton addPartToOrderButton;
    private JLabel genericLabel;
    private JLabel carProblemLabel;
    private JLabel orderPartsLable;
    private JButton findClientButton;
    private JButton findCarButton;
    private JButton createOrderButton;
    private JButton closeOrderButton;
    private JButton orderReadyButton;
    private JButton
    private JTextField findField;




    private JLabel orderLabel, userLabel, clientLabel, brandLabel, serialLabel;



//    private DefaultListModel<PartDto> listPartModel = new DefaultListModel<>();
//    private DefaultListModel<String> listProblemModel = new DefaultListModel<>();

    private JScrollPane scrollPane;
    private JScrollPane scrollPaneOrder;


    private String [] label = {"Order:", "User:", "Client:", "Brand:", "Serial:"};
    private List<JLabel> genericLabels = new ArrayList<>();
    private DefaultTableModel tableModel;
    private DefaultTableModel orderModel;

    private Collection<PartDto> partsDtos = new HashSet<>();
    private List<Integer> orderIds = ServiceOrderController.getInstance().findAllServiceOrderIds();



    private DefaultListModel<Integer> listOrderId= new DefaultListModel<>();

    private ClientDto clientDto;
    private VehicleDto vehicleDto;
    private PartDto partDto;


    public CreateOrderPage(int x, int y, int width, int height) {
        tableModel = new DefaultTableModel();

        orderModel = new DefaultTableModel();
        this.setBounds(x, y, width, height);
        initTransparentPanel();
        initCarProblemLabel();
        initPartsArea();
        initTableDataOrderId();
        initTableServiceOrder();

        tableDataParts();


        intiCarProblemArea();
        initAddProblemField();
        initAddProblemButton();
        initPartsArea();
        //initFindPartField();
        findPartButton();
        initAddPartToOrderButton();
     //   initfindPartsArea();
        initGenerilLabels();
        initOrderPartsLabel();
        initFindClientButton();
        initFindCarButton();
        initFindField();
        //initPartList();
        initCreateOrder();
        initMiniLabels();
        initCloseOrderButton();
        initCount();
        initFindPartArea();
        initOrderReadyButton();


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

    private void initOrderPartsLabel(){
        orderPartsLable = new JLabel("Order parts:");
        orderPartsLable.setBounds(755,10,100,30);
        transparentPanel.add(orderPartsLable);
    }

    private void initTableServiceOrder(){
//
//        orderList = new JList<>(listOrderId);
//        orderList.setBounds(5,50,100,450);
//        transparentPanel.add(orderList);
//        listOrderId.





        orderId = new JTable(orderModel);
        orderId.setBounds(5,50,100,450);
        scrollPaneOrder = new JScrollPane(orderId);
        scrollPaneOrder.setBounds(5,50,100,450);
        transparentPanel.add(scrollPaneOrder);
        orderId.setRowHeight(20);
        orderId.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,15 ));
        orderId.setFont(new Font("Segoe UI", Font.BOLD, 12));
        orderId.getTableHeader().setOpaque(false);
        orderId.getTableHeader().setBackground(new Color(32,136,203));
        orderId.getTableHeader().setForeground(new Color(255,255,255));
        orderId.setShowVerticalLines(false);
        orderId.setSelectionBackground(new Color (232,57,95));

    }

    private void initTableDataOrderId(){
        orderModel.setRowCount(0);

        String [] column = {"Order nr:"};

        orderModel.setColumnIdentifiers(column);

        Object [] row = new Object [1];

        for(Integer integer : orderIds){
            row[0] = integer;

            orderModel.addRow(row);

        }

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

//    private void initPartList(){
//        partList = new JList(listPartModel);
//        partList.setBounds(645,50,300,350);
//        transparentPanel.add(partList);
//    }





    private void initPartsArea(){
        partsArea = new JTable(tableModel);
        partsArea.setBounds(645,50,300,350);
        scrollPane = new JScrollPane(partsArea);
        scrollPane.setBounds(645,50,300,350);
        transparentPanel.add(scrollPane);
        partsArea.setRowHeight(20);
        partsArea.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,15 ));
        partsArea.setFont(new Font("Segoe UI", Font.BOLD, 12));
        partsArea.getTableHeader().setOpaque(false);
        partsArea.getTableHeader().setBackground(new Color(32,136,203));
        partsArea.getTableHeader().setForeground(new Color(255,255,255));
        partsArea.setShowVerticalLines(false);
        partsArea.setSelectionBackground(new Color (232,57,95));





    }


    private void tableDataParts(){

        tableModel.setRowCount(0);

        String [] columns = {"id", "part name", "count", "price"};


        tableModel.setColumnIdentifiers(columns);




        Object [] row = new Object [4];

        for(PartDto partDto : partsDtos){
            row[0] = partDto.getId();
            row[1] = partDto.getPartName();
            row[2] = partDto.getCount();
            row[3] = partDto.getPrice();
            tableModel.addRow(row);

        }
    }



//    private void initfindPartsArea(){
//        findPartArea = new JTable();
//        findPartArea.setBounds(645,410,300,40);
//        transparentPanel.add(findPartArea);
//    }

//    private void initFindPartField(){
//        findPartField = new JTextField();
//        findPartField.setBounds(645,460,300,30);
//        transparentPanel.add(findPartField);
//    }


    private void initCount(){
        countField = new JTextField();
        countField.setBounds(900,460,30,30);
        transparentPanel.add(countField);

        countLabel = new JLabel("count");
        countLabel.setBounds(850,460,50,30);
        transparentPanel.add(countLabel);


    }


    private void initFindPartArea(){
        findPartArea = new JTextArea();
        findPartArea.setBounds(645,405,300,50);
        transparentPanel.add(findPartArea);


    }

    private void initOrderReadyButton(){
        orderReadyButton = new JButton("order Ready");
        orderReadyButton.setBounds(645,460,200,30);
        transparentPanel.add(orderReadyButton);


        orderReadyButton.addActionListener(ev -> {












        });
    }


    private void initAddPartToOrderButton(){
        addPartToOrderButton =new JButton("add part to order");
        addPartToOrderButton.setBounds(645,500,300,30); //430,500,200,30
        transparentPanel.add(addPartToOrderButton);

        addPartToOrderButton.addActionListener(ev -> {

            try{
                int count = Integer.parseInt(countField.getText());

                partDto.setCount(count);

                //:todo de facut cand baga aceeasi piesa dar o alta cantitate sa faca updae daor la count in tabel si la count in baza de date
               if(partsDtos.add(partDto)){
                   PartController.getInstance().decreasePartCount(count, partDto.getPartName());
                   tableDataParts();
                   findPartArea.setText("");
               }



            }catch(NumberFormatException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Enter an integer number ");

            }




        });
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
        findField.setBounds(430,300,200,30);
        transparentPanel.add(findField);
    }

    private void initFindClientButton(){
        findClientButton = new JButton("find client");
        findClientButton.setBounds(430,340,200,30);
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
        findCarButton.setBounds(430,380,200,30);
        transparentPanel.add(findCarButton);

        findCarButton.addActionListener( ev -> {
            vehicleDto = VehicleController.getInstance().findBySerialNumber(findField.getText());
            brandLabel.setText(vehicleDto.getVehicleName());
            serialLabel.setText(vehicleDto.getSerialNumber());



        });

    }

    private void findPartButton(){
        findPartButton = new JButton("find part");
        findPartButton.setBounds(430,420,200,30);
        transparentPanel.add(findPartButton);

        findPartButton.addActionListener( ev-> {
            findPartArea.setText("");
            partDto = PartController.getInstance().findPartByName(findField.getText());
            findPartArea.append(partDto.toString());


        });
    }

    private void initCreateOrder(){
        createOrderButton = new JButton("create order");
        createOrderButton.setBounds(430,460,200,30);
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


            System.out.println(orderIds.toString());
            initTableDataOrderId();
            orderIds.clear();
            orderIds.addAll( ServiceOrderController.getInstance().findAllServiceOrderIds());


        });

    }



    private void initCloseOrderButton(){
        closeOrderButton = new JButton("close order");
        closeOrderButton.setBounds(430,500,200,30);
        transparentPanel.add(closeOrderButton);
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
