package com.ljs.logintest;

import com.ljs.logintest.mapper.MenuMapper;
import com.ljs.logintest.pojo.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class LoginTestApplicationTests {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    void contextLoads() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }

    @Test
    void menu(){
        List<Menu> list = menuMapper.selectMenuTreeByUserId(1);
        System.out.println(list);
    }

}
