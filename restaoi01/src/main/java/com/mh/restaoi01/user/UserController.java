package com.mh.restaoi01.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    유저 조회하는 부분
//    보안을 위해 포스트맵핑 리퀘스트바디를 사용해서 수정한 코드
    @PostMapping("/info")
    public ResponseEntity<User> Userinfo(@RequestBody UserDto userDto){
        String id = userDto.getId();
        User user = userService.userInfo(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
//    처음에 겟맵핑 패스배리어블 사용한 방법
//    @GetMapping("/{id}")
//    public ResponseEntity<User> Userinfo(@PathVariable String id){
//        User user = userService.userInfo(id);
//        return ResponseEntity.status(HttpStatus.OK).body(user);
//    }



//    유저정보 수정하는 부분
    @PutMapping("/update")
//    public ResponseEntity<User> updateUser(@PathVariable("id") String id,@RequestBody @Valid UserDto userDto)
    public ResponseEntity<User> updateUser(@RequestBody @Valid UserDto userDto)
    {
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userDto, User.class);
        User dbUser = userService.update(userDto.getId(),userDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dbUser);
    }



//    삭제 = resign -> Y로 수정
    @PutMapping("/delete")
//    public ResponseEntity<User> resignuser(@PathVariable("id") String id, @RequestParam("password") String password)
    public ResponseEntity<String> resignuser(@RequestBody @Valid UserDto userDto)
    {
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userDto, User.class);
//        User resignuser = userService.resignuser(userDto.getId(), userDto.getPassword());
        String result = userService.resignuser(userDto.getId(), userDto.getPassword());
//        String id = userDto.getId();
//        String password = userDto.getPassword();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }



//    @DeleteMapping("")
//    public ResponseEntity<String> deleteUser(@RequestParam("id") String id, @RequestParam("password") String password)
    //패스배리어블과 리퀘스트파람을 사용한 것 메인
//    @DeleteMapping("deletepath/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable("id") String id, @RequestParam("password") String password)
//    {
//        String result = userService.delete(id, password);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
//    }
//
//    //리퀘스트바디를 사용한 것 서브임
//    @DeleteMapping("delete")
//    public ResponseEntity<String> deleteUser(@RequestBody UserDto userDto){
//
//
////        userService.delete(userDto);
//        System.out.println(userDto);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body("삭제됨");
//    }





}
