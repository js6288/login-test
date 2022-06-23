package com.ljs.logintest.mapper;

import com.ljs.logintest.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ljs
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-06-14 12:59:46
* @Entity com.ljs.logintest.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




