package lib.dto.user;



import lib.dto.autovehicle.ServiceOrderDto;

import java.io.Serializable;
import java.util.*;

public class UserDto implements Serializable {

    private UserIdDto userId;

    private Category category;

    private String password;

    private List<String> phoneNumber = new ArrayList<>(2);

    private List<ServiceOrderDto> serviceOrderDtos = new ArrayList<>();

    public UserDto(UserIdDto userId, Category category, List<String> phoneNumber) {
        this.userId = userId;
        this.category = category;
        this.phoneNumber = phoneNumber;
    }

    public UserDto() {
    }

    public UserIdDto getUserId() {
        return userId;
    }

    public void setUserId(UserIdDto userId) {
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

    public void setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ServiceOrderDto> getServiceOrderDtos() {
        return serviceOrderDtos;
    }

    public void setServiceOrderDtos(List<ServiceOrderDto> serviceOrderDtos) {
        this.serviceOrderDtos = serviceOrderDtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(userId, userDto.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
