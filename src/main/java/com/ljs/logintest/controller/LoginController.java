package com.ljs.logintest.controller;

import com.ljs.logintest.common.domain.AjaxResult;
import com.ljs.logintest.common.utils.SecurityUtils;
import com.ljs.logintest.pojo.LoginBody;
import com.ljs.logintest.pojo.User;
import com.ljs.logintest.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录接口
     * @param loginBody 前端传来的登录对象
     * @return
     */
    @PostMapping("/user/login")
    public AjaxResult login(@RequestBody LoginBody loginBody){
        return loginService.login(loginBody);
    }

    /**
     * 获取当前登录用户的信息
     * @return
     */
    @GetMapping("/user/getUserInfo")
    public AjaxResult getUserInfo(){
        User user = SecurityUtils.getLoginUser().getUser();
        //返回给前端的密码要置空
        user.setPassword("");
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user",user);
        return ajax;
    }

}
