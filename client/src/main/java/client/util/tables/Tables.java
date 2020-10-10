package client.util.tables;

import client.controller.autovehicle.ServiceOrderController;
import client.gui.frame.MainFrame;
import client.util.mouseAdaptors.MouseAdapterButton;
import lib.dto.autovehicle.PartDto;
import lib.dto.autovehicle.ServiceOrderDto;
import lib.dto.autovehicle.Status;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tables {

    private int orderTableX;
    private int orderTableY;
    private int orderTableWidth;
    private int orderTableHeight;
    private boolean flag;
    private int id;
    private double total;
    private Status status;


    private JPanel transparentPanel;

    private JScrollPane scrollPane;
    private JScrollPane scrollPaneOrder;
    private DefaultTableModel tableModel;
    private DefaultTableModel orderModel;

    ServiceOrderDto serviceOrderDto;

    private JTable orderId;
    private JTable partsTable;
    private java.util.List<PartDto> partsDtos = new ArrayList<>();
    private List<Object[]> newOrderIds = new CopyOnWriteArrayList<>(ServiceOrderController.getInstance().findAllServiceOrderIdAndStatus());

    public Tables(int orderTableX, int orderTableY, int tableOrderWidth, int orderTableHeight, JPanel transparentPanel, boolean flag) {
        this.orderTableX = orderTableX;
        this.orderTableY = orderTableY;
        this.orderTableWidth = tableOrderWidth;
        this.orderTableHeight = orderTableHeight;
        this.transparentPanel = transparentPanel;
        this.flag = flag;

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

        initTableServiceOrder();
        initTableDataOrderId();
        initPartsTable();
        tableDataParts();
        selectOrdersWithMouse();
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
                Component cell = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);

                if( value.equals(Status.OPEN)){
                    cell.setForeground(Color.GREEN);
                }

                if(value.equals(Status.CLOSE)){
                    cell.setForeground(Color.BLUE);
                }

                if(value.equals(Status.READY)){
                    cell.setForeground(Color.YELLOW);
                }

                return cell;
            }
        });


        orderId.setBounds(orderTableX,orderTableY,orderTableWidth,orderTableHeight);
        scrollPaneOrder = new JScrollPane(orderId);
        scrollPaneOrder.setBounds(orderTableX,orderTableY,orderTableWidth,orderTableHeight);
        scrollPaneOrder.setBorder(BorderFactory.createLineBorder(MouseAdapterButton.getColorOrange()));
        transparentPanel.add(scrollPaneOrder);
        orderId.setRowHeight(20);
        orderId.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderId.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,15 ));
        orderId.setFont(new Font("Segoe UI", Font.BOLD, 12));
        orderId.getTableHeader().setOpaque(false);
        orderId.getTableHeader().setBackground(new Color(32,136,203));
        orderId.getTableHeader().setForeground(new Color(255,255,255));
        orderId.setShowVerticalLines(false);
        orderId.setBackground(Color.LIGHT_GRAY);
        orderId.setSelectionBackground(new Color (232,57,95));

    }

    public void initTableDataOrderId(){


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



    private void initPartsTable(){
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


    public void tableDataParts(){

        tableModel.setRowCount(0);

        String [] columns = {"id", "part name", "count", "price"};


        tableModel.setColumnIdentifiers(columns);

        Object [] row = new Object [4];

        for (PartDto partsDto : partsDtos) {
            row[0] = partsDto.getId();
            row[1] = partsDto.getPartName();
            row[2] = partsDto.getCount();
            row[3] = partsDto.getPrice();
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
                //   Status col = orderId.columnAtPoint(e.getPoint()); // stergerea liniei previne un bug cand selectam coloana de string in loc de int
                id = (int) orderId.getModel().getValueAt(row, 0);
                status = (Status) orderId.getModel().getValueAt(row, 1);

                if(id != 0 && e.getClickCount() == 1){

                    SwingUtilities.invokeLater(() ->  refreshPartTable(id));

                }
            }
        });
    }

    public void refreshPartTable(int id){

        MainFrame.getInstance().getCreateOrderPage().getCarProblemArea().setText("");
        partsDtos.clear();
         serviceOrderDto = ServiceOrderController.getInstance().findById(id);

        if(!flag){
            SwingUtilities.invokeLater( () -> {
                MainFrame.getInstance().getCreateOrderPage().setGenericLabels(serviceOrderDto); //seteaza labels la createOrder
                serviceOrderDto.getCarProblems()
                        .forEach(s-> MainFrame.getInstance().getCreateOrderPage().getCarProblemArea().append(s + "\n"));
                addPartsToTableAndMakeSum();
            });
        }

        if(flag){
            MainFrame.getInstance().getPartPage().setGenericLabels(serviceOrderDto); //seteaza labels la partPage
            SwingUtilities.invokeLater(() ->{
                addPartsToTableAndMakeSum();

                String c = String.format("%.2f",total);

                MainFrame.getInstance().getPartPage()
                        .getTotalArea().setText("Total:..................................................." + c);
            });
        }
    }

    private void addPartsToTableAndMakeSum(){
        partsDtos.addAll(serviceOrderDto.getParts());
        tableDataParts();
        total =  partsDtos.stream()
                .map(this::totalSum)
                .reduce(0.0, Double::sum);
    }


    private double totalSum(PartDto partDto){
        return partDto.getCount() * partDto.getPrice();
    }

    public void refreshOrderIdTable(List<Object[]> orderIds){
        newOrderIds.clear();
        newOrderIds.addAll(orderIds);
        initTableDataOrderId();
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ServiceOrderDto getServiceOrderDto() {
        return serviceOrderDto;
    }

    public void setServiceOrderDto(ServiceOrderDto serviceOrderDto) {
        this.serviceOrderDto = serviceOrderDto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<PartDto> getPartsDtos() {
        return partsDtos;
    }

    public void setPartsDtos(List<PartDto> partsDtos) {
        this.partsDtos = partsDtos;
    }
}
