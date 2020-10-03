package lib.dto.notification;

import java.io.Serializable;

public class Notification implements Serializable {

    private final String message;

    public Notification(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }


    @Override
    public String toString() {
        return message;
    }
}
