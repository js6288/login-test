package com.ljs.logintest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljs.logintest.pojo.Menu;
import com.ljs.logintest.service.MenuService;
import com.ljs.logintest.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author ljs
* @description 针对表【menu(菜单权限表)】的数据库操作Service实现
* @createDate 2022-06-15 09:43:57
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

    @Override
    public List<Menu> selectMenuTreeByUserId(Integer userId) {
        return this.baseMapper.selectMenuTreeByUserId(userId);
    }

}




