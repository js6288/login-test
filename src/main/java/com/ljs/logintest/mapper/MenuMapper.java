package com.ljs.logintest.mapper;

import com.ljs.logintest.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author ljs
* @description 针对表【menu(菜单权限表)】的数据库操作Mapper
* @createDate 2022-06-15 09:43:57
* @Entity com.ljs.logintest.pojo.Menu
*/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Integer userId);

    List<Menu> selectMenuTreeByUserId(Integer userId);
}




