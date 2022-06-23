package com.ljs.logintest.service;

import com.ljs.logintest.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author ljs
* @description 针对表【menu(菜单权限表)】的数据库操作Service
* @createDate 2022-06-15 09:43:57
*/
public interface MenuService extends IService<Menu> {

    List<Menu> selectMenuTreeByUserId(Integer userId);
}
