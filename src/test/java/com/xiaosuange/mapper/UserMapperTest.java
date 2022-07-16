package com.xiaosuange.service;

import com.xiaosuange.mapper.UserMapper;
import com.xiaosuange.pojo.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void registerTest(){
        System.out.println(userMapper.insert(new Users().setName("ww").setBackgroundImage("img")));
    }
}
