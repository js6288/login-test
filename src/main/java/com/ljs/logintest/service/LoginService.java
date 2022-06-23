package com.ljs.logintest.service;

import com.ljs.logintest.common.domain.AjaxResult;
import com.ljs.logintest.pojo.LoginBody;

public interface LoginService {

    AjaxResult login(LoginBody loginBody);
}
