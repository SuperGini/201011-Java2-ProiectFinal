package client.util.image;

import client.controller.media.PictureController;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ImageTask {

    private JLabel backgroundLabel;
    private int width = 1200;
    private int height = 800;
    private ScheduledExecutorService randomPicture = Executors.newSingleThreadScheduledExecutor();
    private byte [] image;


    public ImageTask(JLabel backgroundLabel) {
        this.backgroundLabel = backgroundLabel;
        scheduleWithFixedDelay();
    }

    //method 1  -> seteaza imaginea ca background
    private void scheduleWithFixedDelay(){
        Runnable task = () -> backgroundLabel.setIcon(getImageIcon());
        randomPicture.scheduleWithFixedDelay(task,0,30, TimeUnit.SECONDS);
    }

    //method 2  -> ia o imagine random din baza de date
    private ImageIcon getImageIcon(){
        image = PictureController.getInstance().getPicture().getPicture();

        Image rescaleImage = new ImageIcon(image).getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(rescaleImage);
    }

    public ScheduledExecutorService getRandomPicture() {
        return randomPicture;
    }

    public byte[] getImage() {
        return image;
    }
}
