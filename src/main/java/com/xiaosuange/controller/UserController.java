package com.xiaosuange.controller;

import com.xiaosuange.controller.returntype.Feedback;
import com.xiaosuange.controller.returntype.UserInfoFeedback;
import com.xiaosuange.controller.returntype.UserLRFeedback;
import com.xiaosuange.pojo.Users;
import com.xiaosuange.service.RelationService;
import com.xiaosuange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/douyin/user")
@ResponseBody
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Feedback Register(String username, String password){
        System.out.println(username+password);
        Users user = userService.register(username, password);
        if(user!=null) {
            return UserLRFeedback.builder()
                    .status_code(Feedback.STATUS_OK)
                    .status_msg(null)
                    .user_id(user.getId())
                    .token(user.getName() +";,"+ user.getSalt())
                    .build();
        } else {
            return Feedback.builder()
                    .status_code(-1)
                    .status_msg("insert failed")
                    .build();
        }
    }

    @PostMapping("/login")
    public Feedback login(String username, String password){
        System.out.println(username+password);
        Users user = userService.login(username, password);
        if(user!=null) {
            return UserLRFeedback.builder()
                    .status_code(Feedback.STATUS_OK)
                    .status_msg(null)
                    .user_id(user.getId())
                    .token(user.getName() +";,"+ user.getSalt())
                    .build();
        } else {
            return Feedback.builder()
                    .status_code(-1)
                    .status_msg("insert failed")
                    .build();
        }
    }
    @GetMapping
    public Feedback getInfo(Long user_id, String token){
        Users me = userService.getMe(token);
        Users targetUser = userService.getInfo(user_id,me.getId());

        if(targetUser==null){
            return Feedback.builder()
                    .status_code(1)
                    .status_msg("no found userinfo")
                    .build();
        } else {
            return UserInfoFeedback.builder()
                    .status_code(0)
                    .user(targetUser)
                    .build();
        }
    }
}
