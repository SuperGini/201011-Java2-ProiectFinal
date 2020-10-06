package client.util.notify;

import AppPackage.AnimationClass;
import client.controller.notification.NotificationController;
import client.gui.frame.MainFrame;
import client.gui.label.pages.NotificationPage;
import client.util.sound.SoundPlay;
import lib.dto.notification.Notification;
import lib.dto.user.UserDto;

import javax.swing.*;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotificationTask {

    private UserDto userDto;
    private ScheduledExecutorService notifyExecutor = Executors.newSingleThreadScheduledExecutor();
    private NotificationPage notificationPage;
    private Timer notificationTimer;
    private int timer;
    private AnimationClass slideEfect = new AnimationClass();
    private SoundPlay soundPlay = new SoundPlay();

    public NotificationTask(NotificationPage notificationPage) {
        this.notificationPage = notificationPage;
        startNotifyExecutor();

    }

    //method 1
    private void startNotifyExecutor(){
        notifyExecutor.scheduleWithFixedDelay(this::task1,20,10, TimeUnit.SECONDS);
    }

    //method 2
    private void task1(){
        Optional.ofNullable(userDto)
                .ifPresentOrElse(this::task2,
                        ()->userDto = MainFrame.getInstance().getAccountPage().getUserDto());
    }

    //method 3
    private void task2(UserDto userDto){
        var notify = NotificationController.getInstance().getNotification(userDto);
        if(!notify.isEmpty()) {

            notificationPage.getOrderNumberLabel().setText(notify.toString());

            notify.forEach(this::setNofificationStatus);

            getNotificationTimer().start();
        }
    }

    //method 4
    private void setNofificationStatus(Notification notification){
        notificationPage.getCategoryLabel().setText(notification.getStatus().toString());
    }

    //metod 5
    private Timer getNotificationTimer(){
        notificationTimer = new Timer(10, event -> notificationTask());
        return notificationTimer;
    }

    //metod 6
    private void notificationTask(){
        timer++;
        if(timer == 1){
            slideEfect.jLabelXLeft(1200,950,1,2,notificationPage);
            soundPlay.getClips().get(4).start();
        }

        if(timer == 300){
            slideEfect.jLabelXRight(950,1200,1,2,notificationPage);
            timer = 0;
            notificationTimer.stop(); //daca bag getNotificationTimer().stop se duce naibii tot => spam fest
            soundPlay.getClips().get(4).stop();
            soundPlay.getClips().get(4).setMicrosecondPosition(0);
        }
    }

    public ScheduledExecutorService getNotifyExecutor() {
        return notifyExecutor;
    }
}
