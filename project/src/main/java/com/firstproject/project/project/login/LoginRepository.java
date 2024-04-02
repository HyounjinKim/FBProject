package com.firstproject.project.project.login;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User,String> {

    User findByIdAndPassword(String id, String pw);
    User findByEmail(String email);
    User findByNameAndEmail(String name, String email);
    User findByIdAndEmail(String id, String email);
    User findByNickname(String nickname);
    User findByPhonenumber(String phonenumber);
}
