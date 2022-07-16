package com.xiaosuange.controller;

import com.xiaosuange.controller.returntype.Feedback;
import com.xiaosuange.controller.returntype.UserListFeedback;
import com.xiaosuange.pojo.Users;
import com.xiaosuange.service.RelationService;
import com.xiaosuange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/douyin/relation")
@ResponseBody
public class RelationController {
    @Autowired
    private RelationService rService;
    @Autowired
    private UserService userService;
    @GetMapping("/follow/list")
    public Feedback followList(Long user_id, String token){
        Users me = userService.getMe(token);
        List<Users> upsList = rService.getUpsList(me.getId(), user_id);
        return UserListFeedback.builder()
                .status_code(0)
                .status_msg(null)
                .user_list(upsList)
                .build();
    }
    @GetMapping("/follower/list")
    public Feedback followerList(Long user_id, String token){
        Users me = userService.getMe(token);
        List<Users> fansList = rService.getFansList(me.getId(), user_id);
        return UserListFeedback.builder()
                .status_code(0)
                .status_msg(null)
                .user_list(fansList)
                .build();
    }

    @PostMapping("/action")
    public Feedback makeRelation(Long to_user_id, String token, Integer action_type){
        Users me = userService.getMe(token);
        if(action_type==1) rService.subscribe(me.getId(),to_user_id);
        else if(action_type==2) rService.disSubscribe(me.getId(),to_user_id);
        return Feedback.builder()
                .status_code(0).status_msg(null)
                .build();
    }
}
