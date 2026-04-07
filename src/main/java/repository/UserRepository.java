package com.deekshith.userapi.repository;

import com.deekshith.userapi.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByNameContainingOrEmailContaining(
            String name,
            String email,
            Pageable pageable
    );
}