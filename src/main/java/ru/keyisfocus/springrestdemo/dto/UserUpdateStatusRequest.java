package ru.keyisfocus.springrestdemo.dto;

import ru.keyisfocus.springrestdemo.models.User;

import javax.validation.constraints.NotNull;

public record UserUpdateStatusRequest(@NotNull(message = "Id is mandatory") Long id,
                                      @NotNull(message = "Status is mandatory") User.UserStatus status) {
}
