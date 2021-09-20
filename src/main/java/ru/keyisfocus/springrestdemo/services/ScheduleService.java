package ru.keyisfocus.springrestdemo.services;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;
import ru.keyisfocus.springrestdemo.models.User;
import ru.keyisfocus.springrestdemo.repositories.UserRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduleService {
    private final UserRepository repository;
    private final TaskScheduler scheduler;
    private final Map<Long, ScheduledFuture<?>> tasks;

    public ScheduleService(UserRepository repository) {
        this.repository = repository;
        scheduler = new ConcurrentTaskScheduler();
        tasks = new ConcurrentHashMap<>();
    }

    public void subscribe(long id) {
        var future = scheduler.schedule(() -> repository.setStatus(id, User.UserStatus.AWAY), Instant.now().plus(5, ChronoUnit.SECONDS));
        tasks.put(id, future);
    }


    public void cancelTask(long id) {
        var task = tasks.get(id);
        if (!task.isDone()) {
            task.cancel(false);
        }
    }
}
