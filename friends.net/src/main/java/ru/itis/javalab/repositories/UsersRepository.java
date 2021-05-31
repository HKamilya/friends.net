package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.model.User;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByUUID(String uuid);


    Optional<User> findByUsername(String username);

    public Optional<User> findByConfirmCode(String code);


    User findByUsernameAndPassword(String username, String password);

}
