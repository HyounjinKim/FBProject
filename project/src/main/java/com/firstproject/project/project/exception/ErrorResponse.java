package com.firstproject.project.project.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss")
    private LocalDateTime errorTime;

}
