package client.gui.label.pages;

import client.gui.panel.TransparentPanel;

import javax.swing.*;

public class NotificationPage extends JLabel {

    private int width;
    private int height;
    private JLabel genericOrderLabel;
    private JLabel genericCategoryLabel;
    private JLabel orderNumberLabel;
    private JLabel categoryLabel;


    private JPanel transparentPanel;

    public NotificationPage(int x, int y, int width, int height) {
        this.height = height;
        this.width = width;
        this.setBounds(x, y, width, height);
        initTransparentPanel();
        initGenericOrderLabel();
        initGenericCategoryLabel();
        initOrderNumberLabel();
        initCategoryLabel();

    }


    private void initTransparentPanel(){
        transparentPanel = new TransparentPanel(0,0,width,height);
        this.add(transparentPanel);
    }

    private void initGenericOrderLabel(){
        genericOrderLabel = new JLabel("Order nr: ");
        genericOrderLabel.setBounds(40,20, 60, 50);
        transparentPanel.add(genericOrderLabel);
    }

    private void initGenericCategoryLabel(){
        genericCategoryLabel = new JLabel("Status: ");
        genericCategoryLabel.setBounds(40,60,60,50);
        transparentPanel.add(genericCategoryLabel);
    }

    private void initOrderNumberLabel(){
        orderNumberLabel = new JLabel();
        orderNumberLabel.setBounds(105, 20,60,50);
        transparentPanel.add(orderNumberLabel);
    }

    private void initCategoryLabel(){
        categoryLabel = new JLabel();
        categoryLabel.setBounds(105, 60,60,50);
        transparentPanel.add(categoryLabel);
    }


    public JLabel getOrderNumberLabel() {
        return orderNumberLabel;
    }

    public void setOrderNumberLabel(JLabel orderNumberLabel) {
        this.orderNumberLabel = orderNumberLabel;
    }

    public JLabel getCategoryLabel() {
        return categoryLabel;
    }

    public void setCategoryLabel(JLabel categoryLabel) {
        this.categoryLabel = categoryLabel;
    }
}
