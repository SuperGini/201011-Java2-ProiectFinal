package server.model.user;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserId implements Serializable {


    private String userName;

    private String emailAdress;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return userName.equals(userId.userName) &&
                emailAdress.equals(userId.emailAdress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, emailAdress);
    }
}
