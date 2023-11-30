package org.example.service;

import org.example.model.User;
import org.example.transport.dto.UserDto;

import java.util.List;

public interface UserService {
    void create(UserDto dto);

    UserDto getById(Long userId);

    List<UserDto> getAll();

    void update(Long userId, UserDto dto);

    void delete(Long userId);
}
