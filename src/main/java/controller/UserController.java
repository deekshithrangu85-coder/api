package com.deekshith.userapi.controller;

import com.deekshith.userapi.entity.User;
import com.deekshith.userapi.dto.UserDTO;
import com.deekshith.userapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 🔹 Pagination + Sorting API
    @GetMapping
    public Page<UserDTO> getUsers(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String sort
    ) {

        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));

        return userService.getUsers(name, email, pageable);
    }

    @GetMapping("/filter")
    public List<User> getUsersByFilter(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String email
    ) {
        return userService.getUsersByNameAndEmail(name, email);
    }

}