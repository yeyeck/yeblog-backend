package com.yeyeck.yeblog.controller;

import com.yeyeck.yeblog.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ExceptionController {

    private static final String MSG_UNAUTHORIZED = "您的权限不够啦，请检查登录信息！";
    private static final String MSG_NOT_FOUND = "抱歉，我没有找到这个数据!";
    // private static final String MSG_FORBIDDEN = "您好像不能访问这个资源！";
    private static final String MSG_BAD_REQUEST = "抱歉，请检查您的输入！";
    private static final String MSG_INTERNAL_SERVER_ERROR = "抱歉，系统开小差了！";
    private static final String MSG_INCORRECT_CREDENTIALS = "用户名或密码错误！";


    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String authorizationException(AuthorizationException e) {
        log.info(e.getMessage());
        return MSG_UNAUTHORIZED;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String authenticationException(AuthenticationException e) {
        log.info(e.getMessage());
        return MSG_UNAUTHORIZED;
    }

    @ExceptionHandler(NoSuchDataException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String noSuchDataException(NoSuchDataException e) {
        log.info(e.getMessage());
        return MSG_NOT_FOUND;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info(e.getMessage());
        return MSG_BAD_REQUEST;
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String incorrectCredentialsException(IncorrectCredentialsException e) {
        log.info(e.getMessage());
        return MSG_INCORRECT_CREDENTIALS;
    }

    @ExceptionHandler(DataConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String dataConflictException(DataConflictException e) {
        return e.getMessage();
    }

    @ExceptionHandler(StatusException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public String statusException(StatusException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EmailAuthenticationInvalidException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public String emailAuthenticationInvalidException(EmailAuthenticationInvalidException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EmailServiceNotSupportException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public String emailServiceNotSupportException(EmailServiceNotSupportException e) {
        return e.getMessage();
    }

    @ExceptionHandler(SystemLimitException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public String systemLimitException(SystemLimitException e) {
        return e.getMessage();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String runtimeException(RuntimeException e) {
        log.info(e.getMessage());
        return MSG_INTERNAL_SERVER_ERROR;
    }
}
