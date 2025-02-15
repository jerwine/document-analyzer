package ie.demo.mapper;

import ie.demo.domain.User;
import ie.demo.model.UserDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
}