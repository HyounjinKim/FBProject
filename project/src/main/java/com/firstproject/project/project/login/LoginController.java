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
@RequestMapping("/login")
@Tag(name = "LoginController",description = "로그인,회원가입,아이디 중복체크,아이디/비밀번호 찾기 기능")
public class LoginController {

    private final LoginService loginService;
    private final LoginRepository loginRepository;

    @Operation(summary = "로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "404",description = "아이디와 비밀번호가 없거나 틀렸을때 나옴")
    })
    @PostMapping
    public ResponseEntity<User> postLogin(@RequestBody LoginInfo loginInfo){
        User dbuser = loginService.findByUser(loginInfo.getId(), loginInfo.getPassword());

        return ResponseEntity.status(HttpStatus.OK).body(dbuser);
    }

    @Operation(summary = "아이디 중복체크")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "아이디가 중복일때 나옴")
    })
    @PostMapping("/make/dupl")
    public ResponseEntity<String> postDuplID(@RequestBody UserIdCheck userIdCheck){

        Optional<User> user =  loginRepository.findById(userIdCheck.getIdCheck());
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("사용가능한 아이디 입니다.");
        }
        throw new LoginException(ErrorCode.DUPLICATIONID);
    }

    @Operation(summary = "회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "CREATED"),
            @ApiResponse(responseCode = "400",description = "정보입력이 잘못됐을때 나옴")
    })
    @PostMapping("/make")
    public ResponseEntity<User> postMakeID(@RequestBody @Valid UserDto userDto){
        userDto.setUser_date(LocalDateTime.now());

        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userDto, User.class);

        User dbuser = loginService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(dbuser);
    }

    @Operation(summary = "아이디 찾기")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "404",description = "입력한 정보가 없을때 나옴")
    })
    @PostMapping("/findid")
    public ResponseEntity<String> getFindID(@RequestBody UserFindId userFindId){
        String id = loginService.findID(userFindId.getName(),userFindId.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @Operation(summary = "비밀번호 찾기")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "404",description = "입력한 정보가 없을때 나옴")
    })
    @PostMapping("/findpw")
    public ResponseEntity<String> getFindPW(@RequestBody UserFindPassword userFindPassword){
        String check = loginService.findPW(userFindPassword.getId(),userFindPassword.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(check);
    }

    @Operation(summary = "비밀번호 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "404",description = "입력한 정보가 없을때 나옴")
    })
    @PutMapping("/findpw")
    public ResponseEntity<User> putFindPW(@RequestBody @Valid PasswordCheck passwordCheck){
        User dbuser = loginService.updatePW(passwordCheck.getId(),passwordCheck.getPassword(),passwordCheck.getPasswordCheck());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dbuser);
    }
}
