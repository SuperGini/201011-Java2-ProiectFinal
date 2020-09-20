package client.gui.label.pages;

import client.controller.autovehicle.PartController;
import client.gui.panel.TransparentPanel;
import lib.dto.autovehicle.PartDto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PartPage extends JLabel {

    private JLabel partLabel;
    private JButton createPartButton;
    private JTextField partField;
    private JTextField priceField;
    private JTextField countField;
    private JButton updatePartCount;
    private JButton addPartToOrder;

    private JPanel transparentPanel;

    private List<JLabel> partLabels = new ArrayList<>();

    public PartPage(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        initTransparentPanel();
        initPartField();
        initPriceField();
        initCountField();
        initPartLabels();
        createPartButton();
        initUpdatePartCountButton();
        initAddPArtToOrderButton();
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
















    private void createPart(){
        PartDto partDto = new PartDto();
        partDto.setPartName(partField.getText());

        try{
            partDto.setPrice(Double.parseDouble(priceField.getText()));
            partDto.setCount(Integer.parseInt(countField.getText()));

            //daca da eroare la format de numar se duce pe catch si nu ne lasa sa creem piesa
            try {
                if (!PartController.getInstance().createPart(partDto)) {
                    JOptionPane.showMessageDialog(null, "Part was added to warehouse");
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



}
