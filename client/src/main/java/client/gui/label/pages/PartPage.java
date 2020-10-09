package client.gui.label.pages;

import client.controller.autovehicle.PartController;
import client.controller.autovehicle.ServiceOrderController;
import client.controller.notification.NotificationController;
import client.gui.button.ZeeButton;
import client.gui.frame.MainFrame;
import client.gui.panel.TransparentPanel;
import client.util.mouseAdaptors.MouseAdapterButton;
import client.util.tables.Tables;
import lib.dto.autovehicle.PartDto;
import lib.dto.autovehicle.ServiceOrderDto;
import lib.dto.autovehicle.Status;
import lib.dto.notification.Notification;
import lib.dto.user.Category;
import lib.dto.user.UserDto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
    private JLabel finalPrice;
    private JLabel genericLabel;
    private JLabel orderLabel, userLabel, clientLabel, brandLabel, serialLabel;

    private List<JLabel> genericLabels = new ArrayList<>();
    private List<Object[]>  newOrderIds = ServiceOrderController.getInstance().findAllServiceOrderIdAndStatus();
    private List<JLabel> partLabels = new ArrayList<>();

    private Tables tables1;

    public PartPage(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        initTransparentPanel();
        initPartField();
        initPriceField();
        initCountField();
        initPartLabels();
        createPartButton();
        initClosePartOrder();
        totalArea();
        initRefreshListButton();
        initGenerilLabels();
        initMiniLabels();
        initFinalPriceLabel();
        initTables();
    }

    private void initTransparentPanel(){
        transparentPanel = new TransparentPanel(250,125,950,550);
        this.add(transparentPanel);
    }

    private void initTables(){
        tables1 = new Tables(525,50,115,350, transparentPanel, true);
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

    public void createPart(){

            try{
                if(!MainFrame.getInstance().getAccountPage()
                        .getUserDto().getCategory().equals(Category.WAREHOUSE)){

                    JOptionPane.showMessageDialog(null, "You dont have permision to add parts \n" +
                            " must have " + Category.WAREHOUSE + " privileges");
                    return;
                }

                if(!tables1.getStatus().equals(Status.CLOSE)){

                    ServiceOrderDto serviceOrderDto = ServiceOrderController.getInstance().findById(tables1.getId());
                                    serviceOrderDto.setTotal(Double.parseDouble(finalPrice.getText()));

                    PartDto partDto = new PartDto();
                            partDto.setPartName(partField.getText());
                            partDto.setPrice(Double.parseDouble(priceField.getText()));
                            partDto.setCount(Integer.parseInt(countField.getText()));
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

                    tables1.refreshPartTable(tables1.getId());
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

            UserDto userDto = MainFrame.getInstance().getAccountPage().getUserDto();

            if(!userDto.getCategory().equals(Category.WAREHOUSE)){
                JOptionPane.showMessageDialog(null, "You dont have permision to close part orders \n" +
                        " must have " + Category.WAREHOUSE + " privileges");
                return;
            }

            if(!tables1.getStatus().equals(Status.CLOSE)){

                int updatePrice = ServiceOrderController.getInstance().setTotalPriceToOrder(tables1.getId(), tables1.getTotal());
                int updateStatus = ServiceOrderController.getInstance().updateServiceOrderStatus(tables1.getId(), Status.READY);

                if (updatePrice > 0 && updateStatus > 0) {

                    Notification notification = new Notification(orderLabel.getText(), Status.READY);

                    //trimite notiifcare catre user body si mechanical
                    NotificationController.getInstance().sendNotificationToUser(userLabel.getText(), notification);
                    refreshOrdersTable();

                    JOptionPane.showMessageDialog(null, "Part order closed" + "\n " + "Total: " + tables1.getTotal());
                } else {
                    JOptionPane.showMessageDialog(null, "Part was not added to the order or status was not updated");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Order is allready" + Status.CLOSE);
            }
        }catch(NullPointerException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Select an order first");
        }
    }

    public void refreshOrdersTable(){

        SwingUtilities.invokeLater(() ->{

            newOrderIds = ServiceOrderController.getInstance().findAllServiceOrderIdAndStatus();
            tables1.refreshOrderIdTable(newOrderIds);
            MainFrame.getInstance().getCreateOrderPage().getTables().refreshOrderIdTable(newOrderIds);
        });
    }

    public void setGenericLabels( ServiceOrderDto serviceOrderDto){
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

    public Tables getTables1() {
        return tables1;
    }

    public void setTables1(Tables tables1) {
        this.tables1 = tables1;
    }

    public JTextArea getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(JTextArea totalArea) {
        this.totalArea = totalArea;
    }

    public JLabel getOrderLabel() {
        return orderLabel;
    }

    public void setOrderLabel(JLabel orderLabel) {
        this.orderLabel = orderLabel;
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

    public JLabel getUserLabel() {
        return userLabel;
    }

    public void setUserLabel(JLabel userLabel) {
        this.userLabel = userLabel;
    }

    public JLabel getSerialLabel() {
        return serialLabel;
    }

    public void setSerialLabel(JLabel serialLabel) {
        this.serialLabel = serialLabel;
    }
}

