package org.example.service;

import liquibase.pro.packaged.L;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.transport.dto.UserDto;
import org.example.transport.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.example.service.util.MockGenerator.generateUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        UserDto userDto = new UserDto();
        User user = new User();

        when(userMapper.toEntity(userDto)).thenReturn(user);

        userService.create(userDto);

        verify(userRepository).save(user);
    }

    @Test
    void getById() {
        Long userId = 1L;
        User user = new User();
        UserDto userDto = new UserDto();

        when(userRepository.getUserById(userId)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDto);

        UserDto result = userService.getById(userId);

        assertEquals(userDto, result);
    }

    @Test
    void getAll() {
        User user = new User();
        UserDto userDto = new UserDto();

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        when(userMapper.toDto(user)).thenReturn(userDto);

        List<UserDto> result = userService.getAll();

        assertEquals(Collections.singletonList(userDto), result);
    }

    @Test
    void update() {
        Long userId = 1L;
        UserDto incomingUser = new UserDto();
        User existingUser = new User();

        when(userRepository.getUserById(userId)).thenReturn(existingUser);

        userService.update(userId, incomingUser);

        verify(userMapper).update(incomingUser, existingUser);
    }

    @Test
    void delete() {
        Long userId = 1L;

        userService.delete(userId);

        verify(userRepository).deleteById(userId);
    }


    }
