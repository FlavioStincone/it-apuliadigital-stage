package it.exprivia.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import it.exprivia.model.User;
import it.exprivia.model.dto.UserDTO;

@Mapper(componentModel = "cdi")
public interface UserMapper {
    
    User toEntity(UserDTO dto);
    
    UserDTO toDTO(User entity);

    List<User> toEntities(List<UserDTO> dtos);

    List<UserDTO> toDTOs(List<User> entities);
    
}
