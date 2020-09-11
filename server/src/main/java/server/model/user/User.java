package server.model.user;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class User {

    @EmbeddedId
    private UserId userId;

    private Category category;

    private String phoneNumbers;


    public User(UserId userId) {
        this.userId = userId;
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
}
