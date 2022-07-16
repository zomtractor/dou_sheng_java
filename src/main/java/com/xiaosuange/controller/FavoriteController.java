package com.xiaosuange.controller;

import com.xiaosuange.controller.returntype.Feedback;
import com.xiaosuange.controller.returntype.VideoListFeedback;
import com.xiaosuange.pojo.Users;
import com.xiaosuange.pojo.Videos;
import com.xiaosuange.service.FavoriteService;
import com.xiaosuange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/douyin/favorite")
@ResponseBody
public class FavoriteController {

    @Autowired
    private UserService userService;
    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/list")
    public Feedback getFavoriteList(Long user_id, String token) {
        Users me = userService.getMe(token);
        List<Videos> ls = favoriteService.getFavoriteList(user_id, me.getId());
        return VideoListFeedback.builder()
                .status_code(0)
                .video_list(ls)
                .build();
    }
    @PostMapping("/action")
    public Feedback favoriteAction(String token,Long video_id,Integer action_type){
        Users me = userService.getMe(token);
        if(action_type==1){
            favoriteService.like(me.getId(),video_id);
        } else if(action_type==2){
            favoriteService.dislike(me.getId(),video_id);
        }
        return Feedback.builder()
                .status_code(0)
                .build();
    }

}
