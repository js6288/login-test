package com.ljs.logintest.common.exception.user;

public class CaptchaException extends RuntimeException{

    public CaptchaException(){
        super("验证码不正确");
    }
}
