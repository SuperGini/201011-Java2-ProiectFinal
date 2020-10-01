package client.gui.label.pages;

import client.controller.autovehicle.PartController;
import client.controller.autovehicle.ServiceOrderController;
import client.gui.panel.TransparentPanel;
import client.util.MouseAdapterButton;
import lib.dto.autovehicle.PartDto;
import lib.dto.autovehicle.ServiceOrderDto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PartPage extends JLabel {

    private JLabel partLabel;
    private JButton createPartButton;
    private JTextField partField;
    private JTextField priceField;
    private JTextField countField;
    private JTextArea totalArea;
    private JTable orderId;
    private JTable partsArea;
    private JButton refreshListButton;
    private JButton refreshPartTable;
    private JButton closePartOrder;
  //  private JLabel totalLabel;
    private JLabel finalPrice;
    private JLabel genericLabel;
    private JLabel orderLabel, userLabel, clientLabel, brandLabel, serialLabel;

    private int id;
    private double total;

    private JPanel transparentPanel;

    private DefaultTableModel tableModel;
    private DefaultTableModel orderModel;

    private JScrollPane scrollPane;
    private JScrollPane scrollPaneOrder;

    private List<JLabel> genericLabels = new ArrayList<>();

    private List<Integer> orderIds = new CopyOnWriteArrayList<>(ServiceOrderController.getInstance().findAllServiceOrderIds());
    private List<PartDto> partsDtos = new CopyOnWriteArrayList<>();


    private List<JLabel> partLabels = new ArrayList<>();

    public PartPage(int x, int y, int width, int height) {

        tableModel = new DefaultTableModel();

        orderModel = new DefaultTableModel();
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


        //initTotalLabel();
        initFinalPriceLabel();
        initClosePartOrder();

        initTableServiceOrder();
        initTableDataOrderId();
        tableDataParts();
        initPartsArea();

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
        createPartButton = new JButton("Add Part");
        createPartButton.setBounds(30,200,190,30);
        createPartButton.addMouseListener(new MouseAdapterButton(createPartButton));
        transparentPanel.add(createPartButton);

        createPartButton.addActionListener(ev -> createPart());
    }

//    private void initUpdatePartCountButton(){
//        updatePartCount = new JButton("Update count number");
//        updatePartCount.setBounds(30,250,190,30);
//        transparentPanel.add(updatePartCount);
//
//        updatePartCount.addActionListener(ev ->updatePartNumber());
//    }

//    private void initAddPArtToOrderButton(){
//        addPartToOrder = new JButton("Add part to order");
//        addPartToOrder.setBounds(30,300,190,30);
//        transparentPanel.add(addPartToOrder);
//        addPartToOrder.addActionListener(ev -> {
//
//            //:todo -> de aici arunc piesa in comanda dupa ce a fost bagata in contextul de persistenta
//            PartDto part = PartController.getInstance().findPartByName(partField.getText());
//            System.out.println(part);
//
//        });
//
//    }

//    private void initTotalLabel(){
//        totalLabel = new JLabel("Total:................................................................");
//        totalLabel.setFont(new Font("Dialog",Font.BOLD, 16));
//        totalLabel.setBounds(645,400,200,30);
//        transparentPanel.add(totalLabel);
//    }


    private void initFinalPriceLabel(){
        finalPrice  = new JLabel("0");
        finalPrice.setFont(new Font("Dialog",Font.BOLD, 16));
        finalPrice.setBounds(850,400,200,30);
        transparentPanel.add(finalPrice);

    }


    private void initRefreshListButton(){
        refreshListButton = new JButton("refresh");
        refreshListButton.setBounds(540,410,100,50);
        refreshListButton.setFont(new Font("Dialog",Font.BOLD, 15));
        transparentPanel.add(refreshListButton);
        refreshListButton.addMouseListener(new MouseAdapterButton(refreshListButton));
        refreshListButton.addActionListener(ev ->{
            orderIds.clear();
            orderIds.addAll(new CopyOnWriteArrayList<>( ServiceOrderController.getInstance().findAllServiceOrderIds()));
            initTableDataOrderId();


        });

    }

    private void totalArea(){
        totalArea = new JTextArea();
        totalArea.setBounds(645, 410, 300, 50);
        totalArea.setFont(new Font("Dialog", Font.BOLD, 15));
        totalArea.setBorder(BorderFactory.createLineBorder(MouseAdapterButton.getColorOrange()));
        transparentPanel.add(totalArea);
    }


    private void initClosePartOrder(){
        closePartOrder = new JButton("close part order");
        closePartOrder.setBounds(540,470,405,40);
        closePartOrder.addMouseListener(new MouseAdapterButton(closePartOrder));
        transparentPanel.add(closePartOrder);

        closePartOrder.addActionListener(ev ->{

          //  double totalPrice = Double.parseDouble(total);

            int updatePrice = ServiceOrderController.getInstance().setTotalPriceToOrder(id,total);

            if(updatePrice > 0){
                JOptionPane.showMessageDialog(null, "Part order close" + "\n " + "Total: " + total);
            }



        });


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












    private void createPart(){
        PartDto partDto = new PartDto();
    //    List<Integer> totalCount = new ArrayList<>();
        partDto.setPartName(partField.getText());

//
//        int row = orderId.getSelectedRow();
//        int id = (int) orderId.getModel().getValueAt(row, 0);
//
            ServiceOrderDto serviceOrderDto = ServiceOrderController.getInstance().findById(id);



        try{

          //  totalCount.add(Integer.parseInt(countField.getText()));

            partDto.setPrice(Double.parseDouble(priceField.getText()));
            partDto.setCount(Integer.parseInt(countField.getText()));

            serviceOrderDto.setTotal(Double.parseDouble(finalPrice.getText()));
            partDto.setServiceOrderDto(serviceOrderDto);




            //daca da eroare la format de numar se duce pe catch si nu ne lasa sa creem piesa
            try {
                if (!PartController.getInstance().createPart(partDto)) {
                    JOptionPane.showMessageDialog(null, "Part added to order");
                    refreshPartTable(id);
                    resetFields();
                }

            }catch(IllegalArgumentException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "The part is allreary in the warehouse");
            }

        }catch(NumberFormatException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Wrong format for the price or count");
        }
    }


    private void updatePartNumber(){
        try{

            int count = Integer.parseInt(countField.getText());
            String partName = partField.getText();

            if(PartController.getInstance().increasePartCount(count, partName) > 0){
                JOptionPane.showMessageDialog(null, "The count for this part was updated");
                refreshPartTable(id);
            }else{
                JOptionPane.showMessageDialog(null, "This part does not exist in the warehouse");
            }

        }catch(NumberFormatException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Wrong format for count, it has to be of type int");
        }
    }


    private void initTableServiceOrder(){






        orderId = new JTable(orderModel);
        orderId.setBounds(540,50,100,350);
        scrollPaneOrder = new JScrollPane(orderId);
        scrollPaneOrder.setBorder(BorderFactory.createLineBorder(MouseAdapterButton.getColorOrange()));
        scrollPaneOrder.setBounds(540,50,100,350);
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

    private void initPartsArea(){
        partsArea = new JTable(tableModel);
        partsArea.setBounds(645,50,300,350);
        scrollPane = new JScrollPane(partsArea);
        scrollPane.setBounds(645,50,300,350);
        scrollPane.setBorder(BorderFactory.createLineBorder(MouseAdapterButton.getColorOrange()));
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

        partsDtos.clear();
        ServiceOrderDto serviceOrderDto = ServiceOrderController.getInstance().findById(id);
        setGenericLabels(serviceOrderDto);

        serviceOrderDto.getParts().stream().forEach(s ->partsDtos.add(s));
        tableDataParts();

             total =  partsDtos.stream()
                .map(this::totalSum)
                .reduce(0.0, Double::sum);


        String c = String.format("%.2f",total);

        totalArea.setText("Total:..................................................." + c);


    }

    private void setGenericLabels( ServiceOrderDto serviceOrderDto){
        orderLabel.setText(String.valueOf(serviceOrderDto.getId()));
        clientLabel.setText(serviceOrderDto.getClientDto().getName());
        brandLabel.setText(serviceOrderDto.getVehicleDtos().getVehicleName());
        serialLabel.setText(serviceOrderDto.getVehicleDtos().getSerialNumber());
        userLabel.setText(serviceOrderDto.getUserDto().getUserId().getUserName());
    }

    private double totalSum(PartDto partDto){
        return partDto.getCount() * partDto.getPrice();
    }

    private void resetFields(){
        partField.setText("");
        priceField.setText("");
        countField.setText("");

    }



}
