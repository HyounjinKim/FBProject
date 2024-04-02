package com.firstproject.project.project.user;

import com.firstproject.project.project.login.LoginInfo;
import com.firstproject.project.project.login.User;
import com.firstproject.project.project.login.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "UserContoroller",description = "사용자 정보 확인,수정,탈퇴 기능")
public class UserController {

    private final UserService userService;


    @Operation(summary = "사용자 정보 확인")
    @PostMapping("/info")
    public ResponseEntity<User> Userinfo(@RequestBody UserDto userDto) {
        User user = userService.userInfo(userDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Operation(summary = "사용자 정보 수정")
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody @Valid UserDto userDto) {
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userDto, User.class);
        User dbUser = userService.update(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dbUser);
    }

    @Operation(summary = "회원탈퇴")
    @PutMapping("/delete")
    public ResponseEntity<String> resignuser(@RequestBody @Valid LoginInfo loginInfo) {
        String result = userService.resignuser(loginInfo.getId(), loginInfo.getPassword());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }

}
