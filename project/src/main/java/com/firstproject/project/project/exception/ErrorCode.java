package com.firstproject.project.project.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    NOTFOUND(HttpStatus.NOT_FOUND,"NOTFOUND","아이디와 비밀번호를 확인해 주세요."),
    DUPLICATIONID(HttpStatus.BAD_REQUEST,"DUPLICATIONID","이미 사용 중인 아이디입니다."),
    DUPLICATIONEMAIL(HttpStatus.BAD_REQUEST,"DUPLICATIONEMAIL","이미 가입된 이메일입니다."),
    DUPLICATIONPHONENUM(HttpStatus.BAD_REQUEST,"DUPLICATIONPHONENUM","이미 가입된 휴대폰번호입니다."),
    IDCHECK(HttpStatus.NOT_FOUND,"IDCHECK","아이디를 확인해 주세요."),
    PASSWORDCHECK(HttpStatus.NOT_FOUND,"PASSWORDCHECK","비밀번호를 확인해 주세요."),
    EMAILCHECK(HttpStatus.NOT_FOUND,"EMAILCHECK","이메일을 확인해 주세요."),
    NAMECHECK(HttpStatus.NOT_FOUND,"NAMECHECK","이름을 확인해 주세요."),
    PHONENUMCHECK(HttpStatus.NOT_FOUND,"PHONENUMCHECK","휴대폰번호를 확인해 주세요."),
    BIRTHDATECHECK(HttpStatus.NOT_FOUND,"BIRTHDATECHECK","생년월일을 확인해 주세요."),
    GENDERERROR(HttpStatus.NOT_FOUND,"GENDERERROR","성별을 확인해 주세요."),
    PASSWORDSIZE(HttpStatus.LENGTH_REQUIRED,"PASSWORDSIZE","패스워드를 8~15자리 사이로 입력해 주세요."),
    INFONOTFOUND(HttpStatus.NOT_FOUND,"INFONOTFOUND","입력하신 정보가 없습니다."),
    PASSWORDDIFFERENT(HttpStatus.BAD_REQUEST,"PASSWORDDIFFERENT","입력하신 패스워드가 다릅니다."),
    PASSWORDPATTERN(HttpStatus.BAD_REQUEST,"PASSWORDPATTERN","비밀번호는 8~15자 영문 대 소문자, 숫자, 특수문자를 사용하세요."),
    DUPLICATIONNICKNAME(HttpStatus.BAD_REQUEST,"DUPLICATIONNICKNAME","이미 사용 중인 닉네임입니다."),
    USERNOTFOUND(HttpStatus.NOT_FOUND,"USERNOTFOUND","검색한 사용자가 없습니다."),
    YOURNICKNAME(HttpStatus.BAD_REQUEST,"YOURNICKNAME","자신의 닉네임은 검색할 수 없습니다.");

    private HttpStatus httpStatus;
    private String errorcode;
    private String message;

    ErrorCode(HttpStatus httpStatus,String errorcode,String message){
        this.httpStatus = httpStatus;
        this.errorcode = errorcode;
        this.message = message;
    }
}
