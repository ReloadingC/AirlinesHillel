package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.transport.dto.UserDto;
import org.example.transport.mapper.UserMapper;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public void create(UserDto dto) {
        User user = userMapper.toEntity(dto);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getById(Long userId) {
        logger.info("Getting user by Id:" + userId);
        User user = Optional.ofNullable(userRepository.getUserById(userId)).orElseThrow(UserNotFoundException::new);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();

    }

    @Override
    public void update(Long userId, UserDto incomingUser) {
        User user = Optional.ofNullable(userRepository.getUserById(userId)).orElseThrow(UserNotFoundException::new);
        userMapper.update(incomingUser, user);
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
