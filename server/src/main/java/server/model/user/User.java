package server.model.user;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.userId.userName = :userName")
public class User {

    @EmbeddedId
    private UserId userId;

    private Category category;

    private String phoneNumbers;


    public User(UserId userId) {
        this.userId = userId;
    }

    public User(){
    }


    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId.getUserName() +
                '}';
    }
}
