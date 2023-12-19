package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.service.UserService;
import org.example.transport.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController  {


        private final UserService userService;

        @PostMapping("/create")
        public void create(@RequestBody UserDto user) {
            userService.create(user);
        }

        @GetMapping("/get/{userId}")
        public UserDto getById(@PathVariable Long userId) {
            return userService.getById(userId);
        }
        @PreAuthorize("hasRole('MANAGER')")
        @GetMapping("/sorted")
        public List<UserDto> getAll() {
            return userService.getAll();
        }

        @PutMapping("/update/{userId}")
        public void update(@PathVariable Long userId, @RequestBody UserDto user) {
            userService.update(userId, user);
        }

        @DeleteMapping("/delete/{userId}")
        public void delete(@PathVariable Long userId) {
            userService.delete(userId);
        }


}
