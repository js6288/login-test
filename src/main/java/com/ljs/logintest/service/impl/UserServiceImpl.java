package com.ljs.logintest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljs.logintest.pojo.User;
import com.ljs.logintest.service.UserService;
import com.ljs.logintest.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author ljs
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-06-14 12:59:46
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




