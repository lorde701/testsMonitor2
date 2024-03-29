package ru.aplana.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aplana.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByName(String name);
}