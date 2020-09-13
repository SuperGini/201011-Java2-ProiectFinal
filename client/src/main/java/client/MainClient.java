package client;

import client.controller.PictureController;
import client.gui.frame.MainFrame;

public class MainClient {
    public static void main(String[] args) {
        new MainFrame();

        System.out.println(PictureController.getInstance().getPicture());
    }
}
