package com.firstproject.project.project.login;


import com.firstproject.project.project.exception.ErrorCode;
import com.firstproject.project.project.exception.LoginException;
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

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("login")
@Tag(name = "LoginController",description = "로그인,회원가입,아이디 중복체크,아이디/비밀번호 찾기 기능")
public class LoginController {

    private final LoginService loginService;
    private final LoginRepository loginRepository;

    @Operation(summary = "로그인")
    @PostMapping
    public ResponseEntity<User> getLogin(@RequestBody LoginInfo loginInfo){
        User dbuser = loginService.findByUser(loginInfo.getId(), loginInfo.getPassword());

        return ResponseEntity.status(HttpStatus.OK).body(dbuser);
    }

    @Operation(summary = "아이디 중복체크")
    @PostMapping("/make/dupl")
    public ResponseEntity<String> getDuplID(@RequestBody UserIdCheck userIdCheck){

        Optional<User> user =  loginRepository.findById(userIdCheck.getIdCheck());
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("사용가능한 아이디 입니다.");
        }
        throw new LoginException(ErrorCode.DUPLICATIONID);
    }

    @Operation(summary = "회원가입")
    @PostMapping("/make")
    public ResponseEntity<User> postMakeID(@RequestBody @Valid UserDto userDto){
        userDto.setUser_date(LocalDateTime.now());

        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userDto, User.class);

        User dbuser = loginService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(dbuser);
    }

    @Operation(summary = "아이디 찾기")
    @PostMapping("/findid")
    public ResponseEntity<String> getFindID(@RequestBody UserFindId userFindId){
        String id = loginService.findID(userFindId.getName(),userFindId.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @Operation(summary = "비밀번호 찾기")
    @PostMapping("/findpw")
    public ResponseEntity<String> getFindPW(@RequestBody UserFindPassword userFindPassword){
        String check = loginService.findPW(userFindPassword.getId(),userFindPassword.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(check);
    }

    @Operation(summary = "비밀번호 수정")
    @PutMapping("/findpw")
    public ResponseEntity<String> putFindPW(@RequestBody @Valid PasswordCheck passwordCheck){
        User dbuser = loginService.updatePW
                (passwordCheck.getId(),passwordCheck.getPassword(),passwordCheck.getPasswordCheck());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("변경된 비밀번호 = "+dbuser.getPassword());
    }
}
