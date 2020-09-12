package server.model.user;

import lib.dto.user.Category;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.userId.userName = :userName")
public class User {

    @EmbeddedId
    private UserId userId;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ElementCollection
    @CollectionTable(name = "user_phoneNumber")
    private Set<String> phoneNumber = new HashSet<>();


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

    public Set<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Set<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId.getUserName() +
                '}';
    }
}
