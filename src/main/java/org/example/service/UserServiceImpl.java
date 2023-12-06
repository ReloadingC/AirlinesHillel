package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.transport.dto.UserDto;
import org.example.transport.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

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
        User user = userRepository.getUserById(userId);
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
        User user = userRepository.getUserById(userId);
        userMapper.update(incomingUser, user);
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
