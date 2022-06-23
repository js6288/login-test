package com.ljs.logintest.controller;


import com.ljs.logintest.common.domain.AjaxResult;
import com.ljs.logintest.common.utils.SecurityUtils;
import com.ljs.logintest.pojo.Menu;
import com.ljs.logintest.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 获取路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters(){
        //获取当前用户id
        Integer userId = SecurityUtils.getLoginUser().getUser().getUserId();
        List<Menu> menuList = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuList);
    }
}
