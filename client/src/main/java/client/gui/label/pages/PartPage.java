package client.gui.label.pages;

import client.controller.autovehicle.PartController;
import client.controller.autovehicle.ServiceOrderController;
import client.controller.notification.NotificationController;
import client.gui.button.ZeeButton;
import client.gui.panel.TransparentPanel;
import client.util.mouseAdaptors.MouseAdapterButton;
import lib.dto.autovehicle.PartDto;
import lib.dto.autovehicle.ServiceOrderDto;
import lib.dto.autovehicle.Status;
import lib.dto.notification.Notification;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PartPage extends JLabel {

    private JPanel transparentPanel;
    private JLabel partLabel;
    private JButton createPartButton;
    private JButton refreshOrdersButton;
    private JButton closePartOrder;
    private JTextField partField;
    private JTextField priceField;
    private JTextField countField;
    private JTextArea totalArea;
    private JTable orderId;
    private JTable partsTable;

    private JLabel finalPrice;
    private JLabel genericLabel;
    private JLabel orderLabel, userLabel, clientLabel, brandLabel, serialLabel;


    private int id;
    private double total;
    private Status status;
    private DefaultTableModel tableModel;
    private DefaultTableModel orderModel;
    private JScrollPane scrollPane;
    private JScrollPane scrollPaneOrder;

    private List<JLabel> genericLabels = new ArrayList<>();
    private List<Object[]>  newOrderIds = new CopyOnWriteArrayList<>(ServiceOrderController.getInstance().findAllServiceOrderIdAndStatus());
    private List<PartDto> partsDtos = new CopyOnWriteArrayList<>();
    private List<JLabel> partLabels = new ArrayList<>();

    public PartPage(int x, int y, int width, int height) {

        tableModel = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        orderModel = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.setBounds(x, y, width, height);
        initTransparentPanel();
        initPartField();
        initPriceField();
        initCountField();
        initPartLabels();
        createPartButton();
        totalArea();
        initRefreshListButton();
        initGenerilLabels();
        initMiniLabels();
        initFinalPriceLabel();
        initClosePartOrder();
        initTableServiceOrder();
        initTableDataOrderId();
        initPartsArea();
        tableDataParts();
        selectOrdersWithMouse();

    }

    private void initTransparentPanel(){
        transparentPanel = new TransparentPanel(250,125,950,550);
        this.add(transparentPanel);
    }

    private void initPartField(){
        partField = new JTextField();
        partField.setBounds(70,50, 150,30);
        partField.setBorder(BorderFactory.createLineBorder(MouseAdapterButton.getColorOrange()));
        transparentPanel.add(partField);
    }

    private void initPriceField(){
        priceField = new JTextField();
        priceField.setBounds(70,100,150,30);
        priceField.setBorder(BorderFactory.createLineBorder(MouseAdapterButton.getColorOrange()));
        transparentPanel.add(priceField);
    }

    private void initCountField(){
        countField = new JTextField();
        countField.setBounds(70,150,150,30);
        countField.setBorder(BorderFactory.createLineBorder(MouseAdapterButton.getColorOrange()));
        transparentPanel.add(countField);
    }

    private void initPartLabels(){
        String [] labelName = {"Part:", "Price:", "Count:"};

        for(int i = 0; i < 3; i++){
            partLabel = new JLabel(labelName[i]);
            partLabel.setBounds(30,50 + (i*50), 50,30);
            transparentPanel.add(partLabel);
            partLabels.add(partLabel);
        }
    }

    private void createPartButton(){
        createPartButton = new ZeeButton(30,200,190,30,"Add Part");
        transparentPanel.add(createPartButton);

    }

    private void initFinalPriceLabel(){
        finalPrice  = new JLabel("0");
        finalPrice.setFont(new Font("Dialog",Font.BOLD, 16));
        finalPrice.setBounds(850,400,200,30);
        transparentPanel.add(finalPrice);

    }

    private void initRefreshListButton(){
        refreshOrdersButton = new ZeeButton(525,410,115,50,"refresh");
        refreshOrdersButton.setFont(new Font("Dialog",Font.BOLD, 15));
        transparentPanel.add(refreshOrdersButton);

    }

    private void totalArea(){
        totalArea = new JTextArea();
        totalArea.setBounds(645, 410, 300, 50);
        totalArea.setEditable(false);
        totalArea.setFont(new Font("Dialog", Font.BOLD, 15));
        totalArea.setBorder(BorderFactory.createLineBorder(MouseAdapterButton.getColorOrange()));
        transparentPanel.add(totalArea);
    }

    private void initClosePartOrder(){
        closePartOrder = new ZeeButton(525,470,445,40,"close part order");
        transparentPanel.add(closePartOrder);
    }

    private void initGenerilLabels(){
        String [] label = {"Order:", "User:", "Client:", "Brand:", "Serial:"};

        for( int i = 0; i < 5; i++){
            genericLabel = new JLabel(label[i]);
            genericLabel.setBounds(350,50 + (i*30), 50,20);
            genericLabels.add(genericLabel);
            transparentPanel.add(genericLabel);
        }
    }

    private void initMiniLabels(){
        orderLabel = new JLabel("");
        orderLabel.setBounds(390,50, 50,20);
        transparentPanel.add(orderLabel);

        userLabel = new JLabel("");
        userLabel.setBounds(390,80, 50,20);
        transparentPanel.add(userLabel);

        clientLabel = new JLabel("");
        clientLabel.setBounds(390,110, 50,20);
        transparentPanel.add(clientLabel);

        brandLabel = new JLabel("");
        brandLabel.setBounds(390,140, 50,20);
        transparentPanel.add(brandLabel);

        serialLabel = new JLabel("");
        serialLabel.setBounds(390,170, 50,20);
        transparentPanel.add(serialLabel);
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

        orderId.setBounds(525,50,115,350);
        scrollPaneOrder = new JScrollPane(orderId);
        scrollPaneOrder.setBorder(BorderFactory.createLineBorder(MouseAdapterButton.getColorOrange()));
        scrollPaneOrder.setBounds(525,50,115,350);
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

        for(PartDto partDto : partsDtos){
            row[0] = partDto.getId();
            row[1] = partDto.getPartName();
            row[2] = partDto.getCount();
            row[3] = partDto.getPrice();
            tableModel.addRow(row);
        }

        for(int i = 0; i< 4; i++){
            partsTable.getColumnModel().getColumn(i).setMaxWidth(75);
            partsTable.getColumnModel().getColumn(i).setMinWidth(75);
        }
    }

    private void selectOrdersWithMouse(){

        orderId.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = orderId.rowAtPoint(e.getPoint());
                //  int col = orderId.columnAtPoint(e.getPoint());
                id = (int) orderId.getModel().getValueAt(row, 0);
                status = (Status) orderId.getModel().getValueAt(row, 1);

                if(id != 0 && e.getClickCount() == 1){
                    System.out.println(status);
                    refreshPartTable(id);
                }
            }
        });
    }


    public void createPart(){

            try{
                if(!status.equals(Status.CLOSE)){

                PartDto partDto = new PartDto();
                partDto.setPartName(partField.getText());
                ServiceOrderDto serviceOrderDto = ServiceOrderController.getInstance().findById(id);


                partDto.setPrice(Double.parseDouble(priceField.getText()));
                partDto.setCount(Integer.parseInt(countField.getText()));

                serviceOrderDto.setTotal(Double.parseDouble(finalPrice.getText()));
                partDto.setServiceOrderDto(serviceOrderDto);

                if(Double.parseDouble(priceField.getText()) < 0){
                    JOptionPane.showMessageDialog(null, "Price must be grater than 0");
                    return;
                }

                if(Integer.parseInt(countField.getText()) < 1){
                    JOptionPane.showMessageDialog(null , "Must add at least 1 part");
                    return;
                }

                //daca da eroare la format de numar se duce pe catch si nu ne lasa sa creem piesa
                if (!PartController.getInstance().createPart(partDto)) {
                    JOptionPane.showMessageDialog(null, "Part added to order");

                    SwingUtilities.invokeLater(() ->  refreshPartTable(id));
                    resetFields();
                }


                }else{
                    JOptionPane.showMessageDialog(null, "Order is " + Status.CLOSE.toString() + " \n" +
                            "you can't add any more parts to it");
                }

            }catch(NumberFormatException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Wrong format for the price or count");
            }catch(IllegalArgumentException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Select a order befor adding a part");
            }catch(NullPointerException e){
                JOptionPane.showMessageDialog(null, "Select an order first");
                e.printStackTrace();
            }
    }

    public void closePartOrder(){

        try {

            if (!status.equals(Status.CLOSE)) {

                int updatePrice = ServiceOrderController.getInstance().setTotalPriceToOrder(id, total);
                int updateStatus = ServiceOrderController.getInstance().updateServiceOrderStatus(id, Status.READY);

                if (updatePrice > 0 && updateStatus > 0) {

                    Notification notification = new Notification(orderLabel.getText(), Status.READY);

                    //trimite notiifcare catre user body si mechanical
                    NotificationController.getInstance().sendNotificationToUser(userLabel.getText(), notification);
                    JOptionPane.showMessageDialog(null, "Part order close" + "\n " + "Total: " + total);
                } else {
                    JOptionPane.showMessageDialog(null, "Part was not added to the order or status was not updated");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Order is " + Status.CLOSE);
            }
        }catch(NullPointerException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Select an order first");
        }
    }

    public void refreashOrdersTable(){

        SwingUtilities.invokeLater(() ->{
            newOrderIds.clear();
            newOrderIds.addAll(ServiceOrderController.getInstance().findAllServiceOrderIdAndStatus());
            initTableDataOrderId();
        });
    }

    //method 1
    private void refreshPartTable(int id){

        SwingUtilities.invokeLater(() -> {
            partsDtos.clear();
            ServiceOrderDto serviceOrderDto = ServiceOrderController.getInstance().findById(id);
            setGenericLabels(serviceOrderDto);
            serviceOrderDto.getParts().forEach(s ->partsDtos.add(s));
            tableDataParts();
            total =  partsDtos.stream()
                    .map(this::totalSum)
                    .reduce(0.0, Double::sum);
            String c = String.format("%.2f",total);

            totalArea.setText("Total:..................................................." + c);
        });
    }

    //method 2
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

    private void resetFields(){
        partField.setText("");
        priceField.setText("");
        countField.setText("");

    }

    public JButton getCreatePartButton() {
        return createPartButton;
    }

    public JButton getRefreshOrdersButton() {
        return refreshOrdersButton;
    }

    public JButton getClosePartOrder() {
        return closePartOrder;
    }
}
