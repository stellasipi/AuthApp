package hu.tbs.authApp.mapper;

import hu.tbs.authApp.dto.UserDTO;
import hu.tbs.authApp.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOtoUser(UserDTO userDTO);
}
