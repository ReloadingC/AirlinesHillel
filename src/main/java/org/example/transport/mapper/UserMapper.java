package org.example.transport.mapper;


import org.example.model.User;
import org.example.transport.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity(UserDto dto);


    void update(UserDto dto, @MappingTarget User user);
}
