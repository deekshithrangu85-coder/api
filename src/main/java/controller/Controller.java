package com.deekshith.userapi.controller;
import com.deekshith.userapi.dto.UserDTO;
import com.deekshith.userapi.entity.User;
import com.deekshith.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import com.deekshith.userapi.entity.User;
import com.deekshith.userapi.dto.UserDTO;
@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Page<UserDTO> getUsers(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String sort
    ) {

        String[] sortParams = sort.split(",");
        String sortField = sortParams[0];
        String sortDirection = sortParams.length > 1 ? sortParams[1] : "asc";

        Sort sorting = sortDirection.equalsIgnoreCase("desc")
                ? Sort.by(sortField).descending()
                : Sort.by(sortField).ascending();

        Pageable pageable = PageRequest.of(page, size, sorting);

        return userService
                .getUsersWithPagination(name, email, pageable)
                .map(this::convertToDTO);
    }@PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}