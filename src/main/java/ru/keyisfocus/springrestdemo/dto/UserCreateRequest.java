package ru.keyisfocus.springrestdemo.dto;

import javax.validation.constraints.NotBlank;

public record UserCreateRequest(@NotBlank(message = "First name is mandatory") String firstName,
                                @NotBlank(message = "Last name is mandatory") String lastName,
                                @NotBlank(message = "Email is mandatory") String email,
                                @NotBlank(message = "Phone is mandatory") String phone) {
}
