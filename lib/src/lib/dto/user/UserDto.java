package lib.dto.user;



import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserDto implements Serializable {

    private UserIdDto userId;

    private Category category;

    private Set<String> phoneNumber = new HashSet<>();

    public UserDto(UserIdDto userId, Category category, Set<String> phoneNumber) {
        this.userId = userId;
        this.category = category;
        this.phoneNumber = phoneNumber;
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

    public Set<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Set<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
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
