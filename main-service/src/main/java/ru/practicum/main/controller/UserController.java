package ru.practicum.main.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.dto.request.user.NewUserRequest;
import ru.practicum.main.dto.response.user.UserDto;
import ru.practicum.main.service.interfaces.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {
    private final UserService service;

    @GetMapping("/admin/users")
    public List<UserDto> getUsers(@RequestParam(required = false) List<Long> ids,
                                  @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") Integer from,
                                  @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        return service.getUsers(ids, pageable);
    }

    @PostMapping("/admin/users")
    public UserDto addUser(@Valid @RequestBody NewUserRequest newUserRequest) {
        return service.addUser(newUserRequest);
    }

    @DeleteMapping("/admin/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        service.deleteUser(userId);
    }

}
