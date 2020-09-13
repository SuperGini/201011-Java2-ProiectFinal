package server.convert;

import lib.dto.user.UserDto;
import lib.dto.user.UserIdDto;
import server.model.user.User;
import server.model.user.UserId;

import java.util.HashSet;

public class UserConvertor {

    private UserConvertor() {
    }

    public static User convert(UserDto userDto){
        User user = new User();
        UserId userId = new UserId();

        userId.setUserName(userDto.getUserId().getUserName());
        userId.setEmailAdress(userDto.getUserId().getEmailAdress());

        user.setUserId(userId);
        user.setCategory(userDto.getCategory());
        user.setPhoneNumber(userDto.getPhoneNumber());

        return user;
    }


    public static UserDto convert(User user){
        UserDto userDto = new UserDto();
        UserIdDto userIdDto = new UserIdDto();


        userIdDto.setUserName(user.getUserId().getUserName());
        userIdDto.setEmailAdress(user.getUserId().getEmailAdress());

        userDto.setUserId(userIdDto);
        userDto.setCategory(user.getCategory());
        userDto.setPhoneNumber(new HashSet<>(user.getPhoneNumber()));

        return userDto;

    }


}
