package lib.dto.notification;

import lib.dto.autovehicle.Status;

import java.io.Serializable;

public class Notification implements Serializable {

    private final String message;

    private Status status;

    public Notification(String message, Status status) {
        this.status = status;
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return message;
    }
}
