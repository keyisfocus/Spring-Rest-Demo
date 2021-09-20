package ru.keyisfocus.springrestdemo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
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
    public ResponseEntity<UserCreateResponse> createUser(
            @Valid @RequestBody UserCreateRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.createUser(request), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<UserGetResponse> getUser(@RequestParam(value = "id") long id) {
        try {
            return new ResponseEntity<>(service.getUser(id), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User Not Found", e);
        }
    }

    @PatchMapping("/update-status")
    public ResponseEntity<UserUpdateStatusResponse> updateUserStatus(
            @Valid @RequestBody UserUpdateStatusRequest request) {

        try {
            return new ResponseEntity<>(service.updateStatus(request), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User Not Found", e);
        }
    }
}
