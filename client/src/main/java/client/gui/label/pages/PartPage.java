package client.gui.label.pages;

import client.controller.autovehicle.PartController;
import client.controller.autovehicle.ServiceOrderController;
import client.gui.panel.TransparentPanel;
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
    private JButton updatePartCount;
    private JButton addPartToOrder;
    private JTable orderId;
    private JTable partsArea;
    private JButton refreshListButton;
    private JButton refreshPartTable;
    private JButton closePartOrder;
    private JLabel totalLabel;
    private JLabel finalPrice;

    private int id;

    private JPanel transparentPanel;

    private DefaultTableModel tableModel;
    private DefaultTableModel orderModel;

    private JScrollPane scrollPane;
    private JScrollPane scrollPaneOrder;

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
        initUpdatePartCountButton();
        initAddPArtToOrderButton();
        initRefreshListButton();
     //   refreshPartTable();
        initTotalLabel();
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
        transparentPanel.add(partField);
    }

    private void initPriceField(){
        priceField = new JTextField();
        priceField.setBounds(70,100,150,30);
        transparentPanel.add(priceField);
    }

    private void initCountField(){
        countField = new JTextField();
        countField.setBounds(70,150,150,30);
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
        transparentPanel.add(createPartButton);

        createPartButton.addActionListener(ev -> createPart());
    }

    private void initUpdatePartCountButton(){
        updatePartCount = new JButton("Update count number");
        updatePartCount.setBounds(30,250,190,30);
        transparentPanel.add(updatePartCount);

        updatePartCount.addActionListener(ev ->updatePartNumber());
    }

    private void initAddPArtToOrderButton(){
        addPartToOrder = new JButton("Add part to order");
        addPartToOrder.setBounds(30,300,190,30);
        transparentPanel.add(addPartToOrder);
        addPartToOrder.addActionListener(ev -> {

            //:todo -> de aici arunc piesa in comanda dupa ce a fost bagata in contextul de persistenta
            PartDto part = PartController.getInstance().findPartByName(partField.getText());
            System.out.println(part);

        });

    }

    private void initTotalLabel(){
        totalLabel = new JLabel("Total:................................................................");
        totalLabel.setFont(new Font("Dialog",Font.BOLD, 16));
        totalLabel.setBounds(645,400,200,30);
        transparentPanel.add(totalLabel);
    }


    private void initFinalPriceLabel(){
        finalPrice  = new JLabel("0");
        finalPrice.setFont(new Font("Dialog",Font.BOLD, 16));
        finalPrice.setBounds(850,400,200,30);
        transparentPanel.add(finalPrice);

    }


    private void initRefreshListButton(){
        refreshListButton = new JButton("refresh");
        refreshListButton.setBounds(30,350,190,30);
        transparentPanel.add(refreshListButton);
        refreshListButton.addActionListener(ev ->{
            orderIds.clear();
            orderIds.addAll(new CopyOnWriteArrayList<>( ServiceOrderController.getInstance().findAllServiceOrderIds()));
            initTableDataOrderId();


        });

    }


    private void initClosePartOrder(){
        closePartOrder = new JButton("close part order");
        closePartOrder.setBounds(545,450,400,40);
        transparentPanel.add(closePartOrder);


    }

//    private void refreshPartTable(){
//        refreshPartTable = new JButton("refresh part table");
//        refreshPartTable.setBounds(30,400,190,30);
//        transparentPanel.add(refreshPartTable);
//
//
//
//    }












    private void createPart(){
        PartDto partDto = new PartDto();
        List<Integer> totalCount = new ArrayList<>();
        partDto.setPartName(partField.getText());

//
//        int row = orderId.getSelectedRow();
//        int id = (int) orderId.getModel().getValueAt(row, 0);
//
            ServiceOrderDto serviceOrderDto = ServiceOrderController.getInstance().findById(id);



        try{

            totalCount.add(Integer.parseInt(countField.getText()));

            partDto.setPrice(Double.parseDouble(priceField.getText()));
            partDto.setCount(Integer.parseInt(countField.getText()));

            serviceOrderDto.setTotal(Double.parseDouble(finalPrice.getText()));
            partDto.setServiceOrderDto(serviceOrderDto);




            //daca da eroare la format de numar se duce pe catch si nu ne lasa sa creem piesa
            try {
                if (!PartController.getInstance().createPart(partDto)) {
                    JOptionPane.showMessageDialog(null, "Part added to order");
                    refreshPartTable(id);
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
            }else{
                JOptionPane.showMessageDialog(null, "This part does not exist in the warehouse");
            }

        }catch(NumberFormatException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Wrong format for count, it has to be of type int");
        }
    }


    private void initTableServiceOrder(){
//
//        orderList = new JList<>(listOrderId);
//        orderList.setBounds(5,50,100,450);
//        transparentPanel.add(orderList);
//        listOrderId.





        orderId = new JTable(orderModel);
        orderId.setBounds(540,50,100,350);
        scrollPaneOrder = new JScrollPane(orderId);
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

        //++++++++++++++++++++aici treubuioe bagata++++++++++++++++++++++++++++++++++++
        serviceOrderDto.getParts().stream().forEach(s ->partsDtos.add(s));
        tableDataParts();

        double x =  partsDtos.stream()
                .map(s ->totalSum(s))
                .reduce(0.0, Double::sum);


        String c = String.format("%.2f",x);

        finalPrice.setText(c);

    }

    private double totalSum(PartDto partDto){
        return partDto.getCount() * partDto.getPrice();
    }



}
