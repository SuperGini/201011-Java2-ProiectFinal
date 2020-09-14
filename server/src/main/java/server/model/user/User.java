package server.model.user;

import lib.dto.user.Category;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.userId.userName = :userName"),
        @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.userId.emailAdress = :emailAdress")
})
public class User {

    @EmbeddedId
    private UserId userId;

    private String password;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ElementCollection
    @CollectionTable(name = "user_phoneNumber")
    private Set<String> phoneNumber = new HashSet<>();


    public User(UserId userId, Category category, Set<String> phoneNumber) {
        this.userId = userId;
        this.category = category;
        this.phoneNumber = phoneNumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId.getUserName() +
                '}';
    }
}
