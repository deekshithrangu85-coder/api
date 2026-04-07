package com.deekshith.userapi.service;

import com.deekshith.userapi.entity.User;
import com.deekshith.userapi.dto.UserDTO;
import com.deekshith.userapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 🔹 Filter (Custom Query)
    public List<User> getUsersByNameAndEmail(String name, String email) {
        return userRepository.findByNameAndEmail(name, email);
    }

    // 🔹 Save User
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // 🔹 Pagination
    public Page<User> getUsersWithPagination(
            String name,
            String email,
            Pageable pageable
    ) {
        return userRepository
                .findByNameContainingOrEmailContaining(name, email, pageable);
    }

    // 🔹 Pagination + DTO (THIS FIXES YOUR ERROR)
    public Page<UserDTO> getUsers(String name, String email, Pageable pageable) {

        Page<User> users = userRepository
                .findByNameContainingOrEmailContaining(name, email, pageable);

        return users.map(user -> new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        ));
    }
}