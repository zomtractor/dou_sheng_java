package com.xiaosuange.service;

import com.xiaosuange.pojo.Comments;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CommentService {
    Comments publishComment(Long uid, Long vid, String content);

    Comments deleteComment(Long uid, Long vid, Long cid);

    List<Comments> getCommentList(Long myId, Long vId);
}
