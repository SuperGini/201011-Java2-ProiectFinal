package lib.dto.user;

import java.io.Serializable;
import java.util.Objects;

public class UserIdDto implements Serializable {

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
        UserIdDto userIdDto = (UserIdDto) o;
        return userName.equals(userIdDto.userName) &&
                emailAdress.equals(userIdDto.emailAdress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, emailAdress);
    }

    @Override
    public String toString() {
        return "UserIdDto{" +
                "userName='" + userName + '\'' +
                ", emailAdress='" + emailAdress + '\'' +
                '}';
    }
}
