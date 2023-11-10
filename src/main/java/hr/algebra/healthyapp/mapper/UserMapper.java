package hr.algebra.healthyapp.mapper;

import hr.algebra.healthyapp.dto.UserDto;
import hr.algebra.healthyapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto sourceToDestination(User source);

    User destinationToSource(UserDto destination);

    List<User> sourceToDestination(List<UserDto> destination);

    List<UserDto> destinationToSource(List<User> destination);
}
