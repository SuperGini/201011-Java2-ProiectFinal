package client.gui.label.pages;

import client.controller.autovehicle.ServiceOrderController;
import client.controller.autovehicle.VehicleController;
import client.controller.notification.NotificationController;
import client.gui.button.ZeeButton;
import client.gui.frame.MainFrame;
import client.gui.panel.TransparentPanel;
import client.util.mouseAdaptors.MouseAdapterButton;
import lib.dto.autovehicle.PartDto;
import lib.dto.autovehicle.ServiceOrderDto;
import lib.dto.autovehicle.Status;
import lib.dto.autovehicle.VehicleDto;
import lib.dto.bill.BillDto;
import lib.dto.bill.TotalPriceDto;
import lib.dto.client.ClientDto;
import lib.dto.notification.Notification;
import lib.dto.user.Category;
import lib.dto.user.UserDto;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CreateOrderPage extends JLabel {

    private JPanel transparentPanel;
    private JTable orderId;
    private JTextArea carProblemArea;
    private JTable partsTable;
    private JTextField addProblemField;
    private JButton addProblemButton;
    private JButton findCarButton;
    private JButton createOrderButton;
    private JButton billButton;
    private JLabel genericLabel;
    private JLabel carProblemLabel;
    private JLabel orderPartsLable;
    private JButton getSelectedORderButton;

    private JTextField findField;
    private int id;
    private int i = 0;
    private double total;

    private JLabel orderLabel, userLabel, clientLabel, brandLabel, serialLabel;

    private JScrollPane scrollPane;
    private JScrollPane scrollPaneOrder;

    private List<JLabel> genericLabels = new ArrayList<>();
    private DefaultTableModel tableModel;
    private DefaultTableModel orderModel;

    private List<PartDto> partsDtos = new ArrayList<>();
    private List<Object[]>  newOrderIds = new CopyOnWriteArrayList<>(ServiceOrderController.getInstance().findAllServiceOrderIdAndStatus());

    private ClientDto clientDto = new ClientDto();
    private VehicleDto vehicleDto = new VehicleDto();
    private ServiceOrderDto serviceOrderDto;
    private BillDto billDto = new BillDto();


    public CreateOrderPage(int x, int y, int width, int height) {
        tableModel = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        orderModel = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        this.setBounds(x, y, width, height);
        initTransparentPanel();
        initCarProblemLabel();
        initPartsArea();
        tableDataParts();
        initTableServiceOrder();
        initTableDataOrderId();

        intiCarProblemArea();
        initAddProblemField();
        initAddProblemButton();
        initPartsArea();
        initGenerilLabels();
        initOrderPartsLabel();
        initFindCarButton();
        initFindField();
        initCreateOrderButton();
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

        orderId = new JTable(orderModel){
            @Override
            public Class<?> getColumnClass(int column) {
                if(convertColumnIndexToModel(column)==1) return String.class;
                return super.getColumnClass(column);
            }
        };


        orderId.setDefaultRenderer(String.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column) {
                Component c = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);

                if( value.equals(Status.OPEN)){
                    c.setForeground(Color.GREEN);
                }

                if(value.equals(Status.CLOSE)){
                    c.setForeground(Color.BLUE);
                }

                if(value.equals(Status.READY)){
                    c.setForeground(Color.YELLOW);
                }

                return c;
            }
        });


        orderId.setBounds(5,50,115,350);
        scrollPaneOrder = new JScrollPane(orderId);
        scrollPaneOrder.setBounds(5,50,115,350);
        scrollPaneOrder.setBorder(BorderFactory.createLineBorder(MouseAdapterButton.getColorOrange()));
        transparentPanel.add(scrollPaneOrder);
        orderId.setRowHeight(20);
        orderId.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,15 ));
        orderId.setFont(new Font("Segoe UI", Font.BOLD, 12));
        orderId.getTableHeader().setOpaque(false);
        orderId.getTableHeader().setBackground(new Color(32,136,203));
        orderId.getTableHeader().setForeground(new Color(255,255,255));
        orderId.setShowVerticalLines(false);
        orderId.setBackground(Color.LIGHT_GRAY);
        orderId.setSelectionBackground(new Color (232,57,95));

    }

    private void initTableDataOrderId(){
        orderModel.setRowCount(0);

        String [] column = {"Order", "Status"};

        orderModel.setColumnIdentifiers(column);


        Object [] row = new Object [2];

        for(Object [] obj : newOrderIds){
            row[0] = obj[0];
            row[1] = obj[1];
            orderModel.addRow(row);

        }

            for(int i = 0; i < 2; i++){
                orderId.getColumnModel().getColumn(i).setMaxWidth(47);
                orderId.getColumnModel().getColumn(i).setMinWidth(47);
            }
    }

    private void intiCarProblemArea(){

        carProblemArea = new JTextArea();
        carProblemArea.setBounds(125,50,290,350);
        carProblemArea.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(carProblemArea);
        scrollPane.setBounds(125,50,290,350);
        scrollPane.setBorder(BorderFactory.createLineBorder(MouseAdapterButton.getColorOrange()));
        transparentPanel.add(scrollPane);
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


    private void initPartsArea(){
        partsTable = new JTable(tableModel);
        partsTable.setBounds(645,50,300,350);
        scrollPane = new JScrollPane(partsTable);
        scrollPane.setBounds(645,50,300,350);
        scrollPane.setBorder(BorderFactory.createLineBorder(MouseAdapterButton.getColorOrange()));
        transparentPanel.add(scrollPane);
        partsTable.setRowHeight(20);
        partsTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,15 ));
        partsTable.setFont(new Font("Segoe UI", Font.BOLD, 12));
        partsTable.getTableHeader().setOpaque(false);
        partsTable.getTableHeader().setBackground(new Color(32,136,203));
        partsTable.getTableHeader().setForeground(new Color(255,255,255));
        partsTable.setShowVerticalLines(false);
        partsTable.setSelectionBackground(new Color (232,57,95));
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

        for(int i = 0; i< 4; i++){
            partsTable.getColumnModel().getColumn(i).setMaxWidth(75);
            partsTable.getColumnModel().getColumn(i).setMinWidth(75);
        }
    }

    public void initBillButton(){
        billButton = new ZeeButton(645,500,300,30,"Bill");
        transparentPanel.add(billButton);
    }



     public void makeBill(){

        String billNumber = String.valueOf(id);
        String path = "./client/src/main/resources/bill/" + billNumber + ".txt";
        billDto.setBrand(clientLabel.getText());
        billDto.setOrderId(orderLabel.getText());
        billDto.setClientName(clientLabel.getText());
        billDto.setSerialNumber(serialLabel.getText());
        TotalPriceDto totalPriceDto = new TotalPriceDto(String.valueOf(total));

        int updateStatus = ServiceOrderController.getInstance().updateServiceOrderStatus(id, Status.CLOSE);

        if(!Files.exists(Paths.get(path))){

            if(updateStatus > 0){
                ServiceOrderController.getInstance().makeBill(partsDtos, path, billDto, totalPriceDto);
                JOptionPane.showMessageDialog(null, "Bill created");
            }

            if(updateStatus == 0){
                JOptionPane.showMessageDialog(null, "Bill was not created");
            }

        }else{
            JOptionPane.showMessageDialog(null, "Bill allready exists");
        }
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

    public void createOrder(){
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

//        serviceOrderDto = new ServiceOrderDto();
//        serviceOrderDto.setClientDto(clientDto);
//        serviceOrderDto.setVehicleDtos(vehicleDto);
//        serviceOrderDto.setStatus(Status.OPEN);
//
//
//        serviceOrderDto.setUserDto(userDto);
      //  userDto.setServiceOrderDtos(List.of(serviceOrderDto));



 //       serviceOrderDto.setCarProblems(lines);


                if(!ServiceOrderController.getInstance().createServiceOrder(serviceOrderDto)){
                    JOptionPane.showMessageDialog(null, "Order created");
                    refreshOrderTable();

                    Object [] obj = newOrderIds.get(newOrderIds.size()-1);
                    int x = (Integer) obj[0];

                    Notification notification = new Notification(String.valueOf(x), Status.OPEN);
                    System.out.println("creted order : " + x);
                    //setez notificarea
                    NotificationController.getInstance().sendNotificationToWarehouse(Category.WAREHOUSE, notification);

                    System.out.println(serviceOrderDto.getId());
                }else{
                    JOptionPane.showMessageDialog(null,"Order was not created");

                }
    }

    private void refreshOrderTable(){

          SwingUtilities.invokeLater(() ->{
              newOrderIds.clear();
              newOrderIds.addAll(ServiceOrderController.getInstance().findAllServiceOrderIdAndStatus());
              initTableDataOrderId();
          });

    }

    private void selectOrdersWithMouse(){

        orderId.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = orderId.rowAtPoint(e.getPoint());
             //   Status col = orderId.columnAtPoint(e.getPoint()); // stergerea liniei previne un bug cand selectam coloana de string in loc de int
                id = (int) orderId.getModel().getValueAt(row, 0);


                if(id != 0 && e.getClickCount() == 1){

                    SwingUtilities.invokeLater(() ->  refreshPartTable(id));

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

        total =  partsDtos.stream()
                .map(this::totalSum)
                .reduce(0.0, Double::sum);
    }

    private double totalSum(PartDto partDto){
        return partDto.getCount() * partDto.getPrice();
    }

    private void setGenericLabels( ServiceOrderDto serviceOrderDto){
        orderLabel.setText(String.valueOf(serviceOrderDto.getId()));
        clientLabel.setText(serviceOrderDto.getClientDto().getName());
        brandLabel.setText(serviceOrderDto.getVehicleDtos().getVehicleName());
        serialLabel.setText(serviceOrderDto.getVehicleDtos().getSerialNumber());
        userLabel.setText(serviceOrderDto.getUserDto().getUserId().getUserName());
    }

    public void addCarProblems(){
        i++;
        carProblemArea.append("\n" + i + "." + addProblemField.getText());
        addProblemField.setText("");
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
        partsDtos.clear();
        tableDataParts();
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
}
