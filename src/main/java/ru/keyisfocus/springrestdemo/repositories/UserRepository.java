package ru.keyisfocus.springrestdemo.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.keyisfocus.springrestdemo.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("update User set status = :status where id = :id")
    @Modifying
    @Transactional
    void setStatus(@Param("id") long id, @Param("status") User.UserStatus status);
}