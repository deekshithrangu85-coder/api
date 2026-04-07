package com.deekshith.userapi.service;
import com.deekshith.userapi.entity.User;
import com.deekshith.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Page<User> getUsersWithPagination(
            String name,
            String email,
            Pageable pageable
    ) {
        return userRepository
                .findByNameContainingOrEmailContaining(name, email, pageable);
    }
}