package com.deekshith.userapi.repository;

import com.deekshith.userapi.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 🔹 Pagination + Search
    Page<User> findByNameContainingOrEmailContaining(
            String name,
            String email,
            Pageable pageable
    );

    // 🔹 Custom Query
    @Query("SELECT u FROM User u WHERE u.name LIKE %:name% AND u.email LIKE %:email%")
    List<User> findByNameAndEmail(
            @Param("name") String name,
            @Param("email") String email
    );
}