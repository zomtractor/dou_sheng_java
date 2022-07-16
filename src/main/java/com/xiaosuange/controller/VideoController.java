package com.xiaosuange.controller;

import com.xiaosuange.controller.returntype.Feedback;
import com.xiaosuange.controller.returntype.VideoListFeedback;
import com.xiaosuange.pojo.Users;
import com.xiaosuange.pojo.Videos;
import com.xiaosuange.service.UserService;
import com.xiaosuange.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/douyin")
@ResponseBody
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private UserService userService;

    @GetMapping("/feed")
    public Feedback feed(Long latest_time,String token){
        Users me = userService.getMe(token);
        if(me==null) me = new Users().setId(0l);
        List<Videos> ls = videoService.getFeed(latest_time,me.getId());
        return VideoListFeedback.builder()
                .status_code(0)
                .video_list(ls)
                .build();
    }
    @PostMapping("publish/action")
    public Feedback publish(String token, String title, MultipartFile data) throws IOException {
        Users me = userService.getMe(token);
        return videoService.saveVideo(me.getId(),title,data.getInputStream())?
                Feedback.builder().status_code(0).build():
                Feedback.builder().status_code(-1).status_msg("save err").build();
    }
    @GetMapping("publish/list")
    public Feedback publishList(String token,Long user_id){
        Users me = userService.getMe(token);
        List<Videos> ls = videoService.getPublishList(user_id, me.getId());
        return VideoListFeedback.builder()
                .status_code(0)
                .video_list(ls)
                .build();
    }
}
