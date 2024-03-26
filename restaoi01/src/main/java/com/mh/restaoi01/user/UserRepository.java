package com.mh.restaoi01.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    // select * from user where id=?

    // select * from user where email = ?
    public User findByEmail(String email);
}
