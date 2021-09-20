package ru.keyisfocus.springrestdemo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.keyisfocus.springrestdemo.dto.UserCreateRequest;
import ru.keyisfocus.springrestdemo.dto.UserCreateResponse;
import ru.keyisfocus.springrestdemo.dto.UserGetResponse;
import ru.keyisfocus.springrestdemo.dto.UserUpdateStatusRequest;
import ru.keyisfocus.springrestdemo.dto.UserUpdateStatusResponse;
import ru.keyisfocus.springrestdemo.exceptions.UserNotFoundException;
import ru.keyisfocus.springrestdemo.services.UserService;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    @PostMapping("/create")
    public ResponseEntity<UserCreateResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        return new ResponseEntity<>(service.createUser(request), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<UserGetResponse> getUser(@RequestParam(value = "id") long id) throws UserNotFoundException {
        return new ResponseEntity<>(service.getUser(id), HttpStatus.OK);
    }

    @PatchMapping("/update-status")
    public ResponseEntity<UserUpdateStatusResponse> updateUserStatus(@Valid @RequestBody UserUpdateStatusRequest request)
            throws UserNotFoundException {
        return new ResponseEntity<>(service.updateStatus(request), HttpStatus.OK);
    }
}
