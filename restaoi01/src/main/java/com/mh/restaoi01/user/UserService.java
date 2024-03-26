package com.mh.restaoi01.user;

import ch.qos.logback.core.spi.ErrorCodes;
import com.mh.restaoi01.exception.ErrorResponse;
import com.mh.restaoi01.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

//    public List<User> getAllUsers() {
//        List<User> list = userRepository.findAll();
//        return list;
//    }

    public User userInfo(String id) {
        Optional<User> userInfomation = userRepository.findById(id);
        if (userInfomation.isEmpty()){
            throw new RuntimeException("검색된 유저가 없습니다");
        }
        else {
            return userInfomation.get();
        }
    }

//        public void delete(UserDto userDto) {
//    public void delete(@RequestParam("id") String id, @RequestParam("password") String password) {
//        Optional<User> dbUser = userRepository.findById(userDto.getId());
//
//        if(userDto.getPassword().equalsIgnoreCase(dbUser.get().getPassword()))
//        {
//            userRepository.deleteById(dbUser.get().getId());
//        }
//
//        if(dbUser.isEmpty()){
//            throw new UsersException(ErrorCode.NOTFOUND);
//        }
//        User getUser = dbUser.get();
//        userRepository.delete(getUser);
//    }

    public String delete(String id, String password) {
        Optional<User> dbUser = userRepository.findById(id);

        if (dbUser.isPresent() && password.equals(dbUser.get().getPassword())) {
            userRepository.deleteById(id);
            return "delete ok ʕ •ᴥ•ʔ ━☆ﾟ";
        }

        return "delete fail ʕ •ᴥ•ʔ ━☆ﾟ";
    }

    public User update(String id,UserDto userDto) {
//        Optional<User> updateuser = userRepository.findById(user.getId());
//        User updateuser = userRepository.findById(user.getId()).orElseThrow(
        User updateuser = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("ʕ •ᴥ•ʔ ━☆ﾟ 유저가 없습니다. ʕ •ᴥ•ʔ ━☆ﾟ")
        );

//        if( updateuser.isEmpty()){
//            System.out.println("수정할수 있는 user 가 없습니다.");
//            throw new RuntimeException("유저 없음");
//        }


        if(userDto.getEmail()!=null)
            updateuser.setEmail(userDto.getEmail());
        if(userDto.getPassword()!=null)
            updateuser.setPassword(userDto.getPassword());
        if (userDto.getNickname()!=null)
            updateuser.setNickname(userDto.getNickname());
        if(userDto.getName()!=null)
            updateuser.setName(userDto.getName());
        if(userDto.getBirthdate()!=null)
            updateuser.setBirthdate(userDto.getBirthdate());
        if (userDto.getPhonenumber()!=null)
            updateuser.setPhonenumber(userDto.getPhonenumber());
        if(userDto.getGender()!=null)
            updateuser.setGender(userDto.getGender());
        if(userDto.getHeight()!=0)
            updateuser.setHeight(userDto.getHeight());
        if(userDto.getWeight()!=0)
            updateuser.setWeight(userDto.getWeight());

        User dbuser = userRepository.save(updateuser);
        return dbuser;
    }


    public String resignuser(String id, String password) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("ʕ •ᴥ•ʔ ━☆ﾟ 유저가 없습니다. ʕ •ᴥ•ʔ ━☆ﾟ"));
        if (password.equals(user.getPassword())){
            user.setResign(resign.Y);

            userRepository.save(user);
            return "ʕ •ᴥ•ʔ ━☆ﾟ 탈퇴 완료되었습니다. ʕ •ᴥ•ʔ ━☆ﾟ";
//            throw new RuntimeException("탈퇴 완료되었습니다");
        }
        else {
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다");
        }
    }
}
