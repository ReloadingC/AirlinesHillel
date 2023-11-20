package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public User getById(Long userId) {
        return userRepository.getUserById(userId);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(Long userId, User incomingUser) {
        User user = getById(userId);

        if(incomingUser.getUsername() != null){
            user.setUsername(incomingUser.getUsername());
        }
        if(incomingUser.getPassword() != null){
            user.setPassword(incomingUser.getPassword());
        }

        return userRepository.save(user);
    }

    @Override
    public void delete(Long userId) {
        User user = getById(userId);
        userRepository.delete(user);
    }
}
