package ru.keyisfocus.springrestdemo.dto;

import lombok.Data;
import ru.keyisfocus.springrestdemo.models.User;

@Data
public class UserGetResponse {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final User.UserStatus status;

    public UserGetResponse(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.status = user.getStatus();
    }
}
