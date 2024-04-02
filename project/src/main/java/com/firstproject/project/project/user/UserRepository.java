package com.firstproject.project.project.user;

import com.firstproject.project.project.login.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    public User findByEmail(String email);

    User findByIdAndPassword(String id, String password);

    Optional<User> findByNickname(String followerNickname);
}
