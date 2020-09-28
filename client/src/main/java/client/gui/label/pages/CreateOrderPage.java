package client.gui.label.pages;

import client.controller.autovehicle.ServiceOrderController;
import client.controller.autovehicle.VehicleController;
import client.gui.frame.MainFrame;
import client.gui.panel.TransparentPanel;
import lib.dto.autovehicle.CountPartDto;
import lib.dto.autovehicle.PartDto;
import lib.dto.autovehicle.ServiceOrderDto;
import lib.dto.autovehicle.VehicleDto;
import lib.dto.client.ClientDto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CreateOrderPage extends JLabel {

    private JPanel transparentPanel;
    private JTable orderId;
    private JTextArea carProblemArea;
    private JTable partsArea;
    private JTextField addProblemField;
    private JButton addProblemButton;
    private JButton findPartButton;
    private JButton addPartToOrderButton;
    private JLabel genericLabel;
    private JLabel carProblemLabel;
    private JLabel orderPartsLable;
    private JButton getSelectedORderButton;
    private JButton findCarButton;
    private JButton createOrderButton;
    private JButton billButton;
    private JTextField findField;
    private int id;
    private int i = 0;


    private JLabel orderLabel, userLabel, clientLabel, brandLabel, serialLabel;


    private JScrollPane scrollPane;
    private JScrollPane scrollPaneOrder;


    private List<JLabel> genericLabels = new ArrayList<>();
    private DefaultTableModel tableModel;
    private DefaultTableModel orderModel;

    private List<PartDto> partsDtos = new ArrayList<>();
    private List<CountPartDto> countPartDtos = new ArrayList<>();





    private List<Integer> orderIds = new CopyOnWriteArrayList<>(ServiceOrderController.getInstance().findAllServiceOrderIds());


    private ClientDto clientDto = new ClientDto();
    private VehicleDto vehicleDto = new VehicleDto();
    private PartDto partDto;
    private ServiceOrderDto serviceOrderDto;


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
        initGenerilLabels();
        initOrderPartsLabel();
        initFindCarButton();
        initFindField();
        initCreateOrder();
        initMiniLabels();
        initBillButton();
        selectOrdersWithMouse();


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
        carProblemArea.setBounds(115,50,300,350);

        JScrollPane scrollPane = new JScrollPane(carProblemArea);
        scrollPane.setBounds(115,50,300,350);
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
           i++;
            carProblemArea.append("\n" + i + "." + addProblemField.getText());
            addProblemField.setText("");
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

        for(int i = 0; i < partsDtos.size(); i++){
            row [0] = partsDtos.get(i).getId();
            row [1] = partsDtos.get(i).getPartName();
            row [2] = partsDtos.get(i).getCount();
            row [3] = partsDtos.get(i).getPrice();
            tableModel.addRow(row);
        }
    }





//
//    private void initCount(){
//        countField = new JTextField();
//        countField.setBounds(900,460,30,30);
//        transparentPanel.add(countField);
//
//        countLabel = new JLabel("count");
//        countLabel.setBounds(850,460,50,30);
//        transparentPanel.add(countLabel);
//
//
//    }


//    private void initFindPartArea(){
//        findPartArea = new JTextArea();
//        findPartArea.setBounds(645,405,300,50);
//        transparentPanel.add(findPartArea);
//
//
//    }

    private void initBillButton(){
        billButton = new JButton("Bill");
        billButton.setBounds(645,500,300,30);
        transparentPanel.add(billButton);


        billButton.addActionListener(ev -> {


              String billNumber = String.valueOf(id);
              String x = "./server/src/main/resources/bill/" + billNumber + ".txt";
            Path billPath = Paths.get("./server/src/main/resources/bill/" + billNumber + ".txt");


            try {

                 Files.createFile(billPath);

                 partsDtos.stream()
                        .forEach(s->makeBill(s,x));


                if(Files.exists(billPath)){
                    JOptionPane.showMessageDialog(null,"Bill created!");
                }

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"Bill error!");
            }

        });
    }

    private void makeBill(PartDto partDto, String x){
            String writeLine = partDto.toString() + "\n";
        PrintStream ps = null;
        try {
            ps = new PrintStream(x);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        ps.println(writeLine);




    }


//    private void initAddPartToOrderButton(){
//        addPartToOrderButton =new JButton("add part to order");
//        addPartToOrderButton.setBounds(645,500,300,30); //430,500,200,30
//        transparentPanel.add(addPartToOrderButton);
//
//        addPartToOrderButton.addActionListener(ev -> {
//
//            if(orderLabel.getText().equals("")){
//                JOptionPane.showMessageDialog(null,"Select an order first before adding a part");
//                return;
//            }
//
//
//            if (!countField.getText().equals("")) {
//
//            try {
//
//                int count = Integer.parseInt(countField.getText());
//
//                CountPartDto countPartDto = new CountPartDto();
//                countPartDto.setCountPartDto(count);
//                countPartDtos.add(countPartDto);
//                countPartDto.setServiceOrderDto(serviceOrderDto);
//                if(!CountPartController.getInstance().createCount(countPartDto)){
//                    JOptionPane.showMessageDialog(null, "S-a creat count");
//                }
//
//
//
//
//
//                //:todo de facut cand baga aceeasi piesa dar o alta cantitate sa faca updae daor la count in tabel si la count in baza de date
//                if (partsDtos.add(partDto)) {
//                    PartController.getInstance().decreasePartCount(count, partDto.getPartName());
//                    tableDataParts();
//                    findPartArea.setText("");
//
//                }
//
//
//
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//                JOptionPane.showMessageDialog(null, "Enter an integer number ");
//            }catch(NullPointerException e){
//                JOptionPane.showMessageDialog(null, "Find a part first");
//                e.printStackTrace();
//            }
//
//        }else{
//                JOptionPane.showMessageDialog(null, "Enter part count number");
//            }
//
//        });
//    }




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
        transparentPanel.add(findField);
    }

//    private void initGetSelectedOrder(){
//        getSelectedORderButton = new JButton("get selected order");
//        getSelectedORderButton.setBounds(430,340,200,30);
//        transparentPanel.add(getSelectedORderButton);
//
//        getSelectedORderButton.addActionListener( ev ->{
//
//            int row = orderId.getSelectedRow();
//            int id = (int) orderId.getModel().getValueAt(row, 0);
//
//            partsDtos.clear();
//
//            carProblemArea.setText("");
//
//            serviceOrderDto = ServiceOrderController.getInstance().findById(id);
//
//
//            orderLabel.setText(String.valueOf(serviceOrderDto.getId()));
//            clientLabel.setText(serviceOrderDto.getClientDto().getName());
//            brandLabel.setText(serviceOrderDto.getVehicleDtos().getVehicleName());
//            serialLabel.setText(serviceOrderDto.getVehicleDtos().getSerialNumber());
//
//
//            carProblemArea.append(serviceOrderDto.getCarProblems().toString());
//            partsDtos.addAll(new CopyOnWriteArrayList<>(serviceOrderDto.getParts()));
//            tableDataParts();
//
//            System.out.println(serviceOrderDto.getId());
//
//        });
//    }

    private void initFindCarButton(){
        findCarButton = new JButton("find car serial");
        findCarButton.setBounds(430,350,200,30);
        transparentPanel.add(findCarButton);

        findCarButton.addActionListener( ev -> {

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

        });

    }

//    private void findPartButton(){
//        findPartButton = new JButton("find part");
//        findPartButton.setBounds(430,420,200,30);
//        transparentPanel.add(findPartButton);
//
//        findPartButton.addActionListener( ev-> {
//
//            if(!findField.getText().equals("")){
//
//                try{
//                    findPartArea.setText("");
//                    partDto = PartController.getInstance().findPartByName(findField.getText());
//                    findPartArea.append(partDto.toString());
//
//                }catch(NoSuchElementException e){
//                    e.printStackTrace();
//                    JOptionPane.showMessageDialog(null,"No part by that name was found");
//                }
//            }else{
//                JOptionPane.showMessageDialog(null,"Enter part name");
//            }
//        });
//    }

    private void initCreateOrder(){
        createOrderButton = new JButton("create order");
        createOrderButton.setBounds(430,500,200,30);
        transparentPanel.add(createOrderButton);

        createOrderButton.addActionListener(ev ->{

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


        });
    }

    private void createNewOrder(){

                 serviceOrderDto = new ServiceOrderDto();

        serviceOrderDto.setClientDto(clientDto);
        serviceOrderDto.setVehicleDtos(vehicleDto);


        serviceOrderDto.setUserDto(MainFrame.getInstance().getAccountPage().getUserDto());


                String text = carProblemArea.getText();
                String [] textLines = text.split("\n");
                List<String> lines = Arrays.asList(textLines);

        serviceOrderDto.setCarProblems(lines);


                if(!ServiceOrderController.getInstance().createServiceOrder(serviceOrderDto)){
                    JOptionPane.showMessageDialog(null, "Order created");


                    //aici++++++++++++++++++++++++++++++++++++++++++++++++

                    orderIds.clear();
                    orderIds.addAll(new CopyOnWriteArrayList<>( ServiceOrderController.getInstance().findAllServiceOrderIds()));
                    System.out.println(orderIds.toString() + "++++++");
                    initTableDataOrderId();
                    System.out.println(serviceOrderDto.getId());
                }else{
                    JOptionPane.showMessageDialog(null,"Order was not created");

                }
    }

    private void selectOrdersWithMouse(){

        orderId.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = orderId.rowAtPoint(e.getPoint());
                int col = orderId.columnAtPoint(e.getPoint());
                id = (int) orderId.getModel().getValueAt(row, col);

                if(id != 0 && e.getClickCount() == 1){

                    refreshPartTable(id);

                }
            }
        });
    }

    private void refreshPartTable(int id){
        carProblemArea.setText("");
        partsDtos.clear();
        ServiceOrderDto serviceOrderDto = ServiceOrderController.getInstance().findById(id);
        setGenericLabels(serviceOrderDto);
        serviceOrderDto.getCarProblems().stream()
                .forEach(s->carProblemArea.append(s + "\n"));

        serviceOrderDto.getParts().stream().forEach(s ->partsDtos.add(s));
        tableDataParts();


    }

    private void setGenericLabels( ServiceOrderDto serviceOrderDto){
        orderLabel.setText(String.valueOf(serviceOrderDto.getId()));
        clientLabel.setText(serviceOrderDto.getClientDto().getName());
        brandLabel.setText(serviceOrderDto.getVehicleDtos().getVehicleName());
        serialLabel.setText(serviceOrderDto.getVehicleDtos().getSerialNumber());
        userLabel.setText(serviceOrderDto.getUserDto().getUserId().getUserName());
    }



    private void resetFields(){
        orderLabel.setText("");
        clientLabel.setText("");
        brandLabel.setText("");
        serialLabel.setText("");
        findField.setText("");
        partsDtos.clear();
        tableDataParts();
        addProblemField.setText("");
        carProblemArea.setText("");
    }




//    private void initCloseOrderButton(){
//        closeOrderButton = new JButton("close order");
//        closeOrderButton.setBounds(430,500,200,30);
//        transparentPanel.add(closeOrderButton);
//    }

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

    public void setCreateOrderButton(JButton createOrderButton) {
        this.createOrderButton = createOrderButton;
    }
}
