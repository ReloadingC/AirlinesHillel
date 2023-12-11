package org.example.service.util;

import org.example.model.User;
import org.example.transport.dto.UserDto;

public class MockGenerator {

    public static UserDto generateUser(String username, String password){
        UserDto user = new UserDto();
        user.setId(1L);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
