package ru.keyisfocus.springrestdemo.dto;

import ru.keyisfocus.springrestdemo.models.User;

public record UserUpdateStatusResponse(long id,
                                       User.UserStatus previousStatus,
                                       User.UserStatus currentStatus) {
}
