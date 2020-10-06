package server.model.user;

import lib.dto.user.Category;
import server.model.autovehicle.ServiceOrder;

import javax.persistence.*;
import java.util.*;

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
    private List<String> phoneNumber = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private Collection<ServiceOrder> serviceOrders = new ArrayList<>();


    public User(UserId userId, Category category, List<String> phoneNumber) {
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

    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(Collection<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId.getUserName() + " " +getPhoneNumber().toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
