package server.convert.user;

import lib.dto.user.UserDto;
import lib.dto.user.UserIdDto;
import server.model.autovehicle.ServiceOrder;
import server.model.user.User;
import server.model.user.UserId;

import java.util.ArrayList;

public class UserConvertor {

    private UserConvertor() {
    }

    public static User convert(UserDto userDto){

        UserId userId = new UserId();
                userId.setUserName(userDto.getUserId().getUserName());
                userId.setEmailAdress(userDto.getUserId().getEmailAdress());

        User user = new User();
                user.setUserId(userId);
                user.setCategory(userDto.getCategory());
                user.setPhoneNumber(userDto.getPhoneNumber());
                user.setPassword(userDto.getPassword());

        return user;
    }


    public static UserDto convert(User user){

        UserIdDto userIdDto = new UserIdDto();
                    userIdDto.setUserName(user.getUserId().getUserName());
                    userIdDto.setEmailAdress(user.getUserId().getEmailAdress());

        UserDto userDto = new UserDto();
                    userDto.setUserId(userIdDto);
                    userDto.setCategory(user.getCategory());
                    userDto.setPassword(user.getPassword());
                    userDto.setPhoneNumber(new ArrayList<>(user.getPhoneNumber()));

        return userDto;
    }

    public static UserDto convertNoPhoneNumbers(ServiceOrder serviceOrder){

        UserIdDto userIdDto = new UserIdDto();
                    userIdDto.setUserName(serviceOrder.getUser().getUserId().getUserName());
                    userIdDto.setEmailAdress(serviceOrder.getUser().getUserId().getEmailAdress());

        UserDto userDto = new UserDto();
                userDto.setUserId(userIdDto);


        return userDto;
    }


}
