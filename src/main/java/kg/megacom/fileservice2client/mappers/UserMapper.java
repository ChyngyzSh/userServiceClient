package kg.megacom.fileservice2client.mappers;
import kg.megacom.fileservice2client.models.User;
import kg.megacom.fileservice2client.models.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDto userDto);
    UserDto toDto(User user);

    List<User> toEntities(List<UserDto>userDtoList);

    List<UserDto> toDtos(List<User>userList);



}

//E toEntity(D d);
//    D toDto(E e);
//
//    List<E> toEntities(List<D>d);
//    List<D> toDtos(List<E>e);