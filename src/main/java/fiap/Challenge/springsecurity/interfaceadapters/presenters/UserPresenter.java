package fiap.Challenge.springsecurity.interfaceadapters.presenters;

import fiap.Challenge.springsecurity.entities.User;
import fiap.Challenge.springsecurity.interfaceadapters.presenters.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserPresenter implements Presenter<User, UserDto> {

    @Override
    public UserDto convert(User user){

        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());

        return dto;
    }

    @Override
    public User convert(UserDto dto) {

        User user = new User();

        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        return user;
    }
}
