package ru.keyisfocus.springrestdemo.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.keyisfocus.springrestdemo.dto.UserCreateRequest;
import ru.keyisfocus.springrestdemo.dto.UserCreateResponse;
import ru.keyisfocus.springrestdemo.dto.UserGetResponse;
import ru.keyisfocus.springrestdemo.dto.UserUpdateStatusRequest;
import ru.keyisfocus.springrestdemo.dto.UserUpdateStatusResponse;
import ru.keyisfocus.springrestdemo.exceptions.UserNotFoundException;
import ru.keyisfocus.springrestdemo.models.User;
import ru.keyisfocus.springrestdemo.repositories.UserRepository;

import java.time.Instant;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository repository;
    private ScheduleService scheduleService;

    public UserCreateResponse createUser(UserCreateRequest request) {
        var user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phone(request.phone())
                .build();

        return new UserCreateResponse(repository.save(user).getId());
    }

    public UserGetResponse getUser(long id) throws UserNotFoundException {
        return new UserGetResponse(findUserById(id));
    }

    public UserUpdateStatusResponse updateStatus(UserUpdateStatusRequest request) throws UserNotFoundException {
        var user = findUserById(request.id());
        var previousStatus = user.getStatus();
        user.setStatus(request.status());
        user.setLastStatusUpdateTimestamp(Instant.now());
        repository.save(user);
        if (user.getStatus() == User.UserStatus.ONLINE) {
            scheduleService.subscribe(user.getId());
        } else {
            scheduleService.cancelTask(user.getId());
        }

        return new UserUpdateStatusResponse(user.getId(), previousStatus, user.getStatus());
    }

    private User findUserById(Long id) throws UserNotFoundException {
        return repository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User #%d doesn't exist", id)));
    }
}
