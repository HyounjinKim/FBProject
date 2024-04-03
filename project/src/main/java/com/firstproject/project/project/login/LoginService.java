package com.firstproject.project.project.login;


import com.firstproject.project.project.exception.ErrorCode;
import com.firstproject.project.project.exception.LoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public User findByUser(String id, String pw) {
        User user = loginRepository.findByIdAndPassword(id, pw);

        if (user == null) {
            throw new LoginException(ErrorCode.NOTFOUND);
        }
        if (user.getResign().equals(Resign.Y)) {
            throw new LoginException(ErrorCode.INFONOTFOUND);
        }

        return user;
    }

    public User createUser(User user) {
        User dupemail = loginRepository.findByEmail(user.getEmail());
        User dupnickname = loginRepository.findByNickname(user.getNickname());
        User dupphonenum = loginRepository.findByPhonenumber(user.getPhonenumber());
        Optional<User> dupeid = loginRepository.findById(user.getId());

        if (!dupeid.isEmpty()) {
            throw new LoginException(ErrorCode.DUPLICATIONID);
        } else if (dupemail != null) {
            throw new LoginException(ErrorCode.DUPLICATIONEMAIL);
        } else if (dupnickname != null) {
            throw new LoginException(ErrorCode.DUPLICATIONNICKNAME);
        } else if (dupphonenum != null) {
            throw new LoginException(ErrorCode.DUPLICATIONPHONENUM);
        } else if (user.getId() == null) {
            throw new LoginException(ErrorCode.IDCHECK);
        } else if (user.getPassword() == null) {
            throw new LoginException(ErrorCode.PASSWORDCHECK);
        } else if (user.getEmail() == null) {
            throw new LoginException(ErrorCode.EMAILCHECK);
        } else if (user.getName() == null) {
            throw new LoginException(ErrorCode.NAMECHECK);
        } else if (user.getGender() == null) {
            throw new LoginException(ErrorCode.GENDERERROR);
        } else if (user.getPhonenumber() == null) {
            throw new LoginException(ErrorCode.PHONENUMCHECK);
        } else if (user.getBirthdate() == null) {
            throw new LoginException(ErrorCode.BIRTHDATECHECK);
        } else if (user.getPassword().length() < 8
                || user.getPassword().length() > 15) {
            throw new LoginException(ErrorCode.PASSWORDSIZE);
        } else if (!user.getPassword().equals(user.getPasswordch())) {
            throw new LoginException(ErrorCode.PASSWORDDIFFERENT);
        }
        User dbuser = loginRepository.save(user);

        return dbuser;
    }

    public String findID(String name, String email) {
        User user = loginRepository.findByNameAndEmail(name, email);
        if (user == null) {
            throw new LoginException(ErrorCode.INFONOTFOUND);
        }
        return user.getId();
    }

    public String findPW(String id, String email) {
        User user = loginRepository.findByIdAndEmail(id, email);

        if (user == null) {
            throw new LoginException(ErrorCode.INFONOTFOUND);
        }
        return "OK";
    }

    public User updatePW(String id, String password, String passwordCheck) {
        Optional<User> user = loginRepository.findById(id);

        if (user.isEmpty()) {
            throw new LoginException(ErrorCode.IDCHECK);
        }

        User pwUser = user.get();

        if (!password.equals(passwordCheck)) {
            throw new LoginException(ErrorCode.PASSWORDDIFFERENT);
        }
        if (password.length() < 8 || password.length() > 15) {
            throw new LoginException(ErrorCode.PASSWORDSIZE);
        }
        pwUser.setPassword(password);

        User dbuser = loginRepository.save(pwUser);

        return dbuser;
    }
}
