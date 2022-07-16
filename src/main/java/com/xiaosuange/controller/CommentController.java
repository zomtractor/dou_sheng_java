package com.xiaosuange.controller;

import com.xiaosuange.controller.returntype.CommentFeedback;
import com.xiaosuange.controller.returntype.CommentListFeedback;
import com.xiaosuange.controller.returntype.Feedback;
import com.xiaosuange.pojo.Comments;
import com.xiaosuange.pojo.Users;
import com.xiaosuange.service.CommentService;
import com.xiaosuange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/douyin/comment")
@ResponseBody
public class CommentController {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @PostMapping("/action")
    public Feedback commentAction(String token, Long video_id, Integer action_type, String comment_text, Long comment_id) {
        Users me = userService.getMe(token);
        Comments cmt = null;
        if (action_type == 1) {
            cmt = commentService.publishComment(me.getId(), video_id, comment_text);
        } else if (action_type == 2) {
            cmt = commentService.deleteComment(me.getId(), video_id, comment_id);
        }
        return CommentFeedback.builder()
                .status_code(0)
                .comment(cmt)
                .build();
    }

    @GetMapping("/list")
    public Feedback commentList(String token, Long video_id) {
        Users me = userService.getMe(token);
        List<Comments> list = commentService.getCommentList(me.getId(), video_id);
        return CommentListFeedback.builder()
                .status_code(0)
                .comment_list(list).build();
    }

}
