package com.firstproject.project.project.user;

import com.firstproject.project.project.exception.ErrorCode;
import com.firstproject.project.project.exception.LoginException;
import com.firstproject.project.project.login.Resign;
import com.firstproject.project.project.login.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User userInfo(String id) {
        Optional<User> userInfomation = userRepository.findById(id);
        if (userInfomation.isEmpty()) {
            throw new RuntimeException("검색된 유저가 없습니다");
        } else {
            return userInfomation.get();
        }
    }

    public User update(User user) {
        User updateuser = userRepository.findById(user.getId()).orElseThrow(
                () -> new LoginException(ErrorCode.INFONOTFOUND));

        if (user.getEmail() != null)
            updateuser.setEmail(user.getEmail());
        if (user.getPassword() != null)
            updateuser.setPassword(user.getPassword());
        if (user.getNickname() != null)
            updateuser.setNickname(user.getNickname());
        if (user.getName() != null)
            updateuser.setName(user.getName());
        if (user.getBirthdate() != null)
            updateuser.setBirthdate(user.getBirthdate());
        if (user.getPhonenumber() != null)
            updateuser.setPhonenumber(user.getPhonenumber());
        if (user.getGender() != null)
            updateuser.setGender(user.getGender());
        if (user.getHeight() != 0)
            updateuser.setHeight(user.getHeight());
        if (user.getWeight() != 0)
            updateuser.setWeight(user.getWeight());

        User dbuser = userRepository.save(updateuser);
        return dbuser;
    }

    public String resignuser(String id, String password) {
        User user = userRepository.findByIdAndPassword(id,password);

        if (user == null){
            throw new LoginException(ErrorCode.INFONOTFOUND);
        }
        if(user.getResign().equals(Resign.Y)){
            throw new LoginException(ErrorCode.INFONOTFOUND);
        }

        user.setResign(Resign.Y);
        userRepository.save(user);

        return "ʕ •ᴥ•ʔ ━☆ﾟ 탈퇴 완료되었습니다. ʕ •ᴥ•ʔ ━☆ﾟ";
    }
}
