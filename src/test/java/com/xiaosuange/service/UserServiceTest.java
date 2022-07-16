package com.xiaosuange.service;

import com.xiaosuange.mapper.UserMapper;
import com.xiaosuange.pojo.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void registerTest(){
        Users i = null;
        try{
            i=userService.register("77","1234");
        } catch (Exception e){
            System.out.println("err.");
        } finally {
            System.out.println(i);
        }
    }
    @Test
    public void loginTest(){
        Users i = null;
        try{
            i=userService.login("77","1234");
        } catch (Exception e){
            System.out.println("err.");
        } finally {
            System.out.println(i);
        }
    }
    @Test
    public void test1(){
        Users login = userService.login("77", "1234");
        System.out.println(login);
    }
    @Test
    public void getInfoTest(){
        Users i = null;
        try{
            i=userService.login("77","1234");
        } catch (Exception e){
            System.out.println("err.");
        } finally {
            System.out.println(i);
        }
    }
}
