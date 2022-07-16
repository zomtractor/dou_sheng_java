package com.xiaosuange.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosuange.mapper.CommentMapper;
import com.xiaosuange.mapper.UserMapper;
import com.xiaosuange.mapper.VideoMapper;
import com.xiaosuange.mapper.XMapper;
import com.xiaosuange.pojo.Comments;
import com.xiaosuange.pojo.Users;
import com.xiaosuange.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserMapper uMapper;
    @Autowired
    private CommentMapper cMapper;
    @Autowired
    private VideoMapper vMapper;
    @Autowired
    private XMapper xMapper;
    @Override
    public Comments publishComment(Long uid,Long vid,String content){
        Comments cmt = new Comments().setUserId(uid)
                .setUser(uMapper.selectById(uid))
                .setVideoId(vid)
                .setContent(content)
                .setCreateTime(System.currentTimeMillis()/1000);
        cMapper.insert(cmt);
        vMapper.updateById(vMapper.selectById(vid).addCommentCount());
        return cmt;
    }
    @Override
    public Comments deleteComment(Long uid, Long vid, Long cid){
        int i = cMapper.deleteById(cid);
        if(i==0) return null;
        vMapper.updateById(vMapper.selectById(vid).subCommentCount());
        return null;
    }
    @Override
    public List<Comments> getCommentList(Long myId,Long vId){
        List<Comments> list = cMapper.selectList(new QueryWrapper<Comments>().eq("video_id", vId));
        for(Comments c:list){
            Users user = uMapper.selectById(c.getUserId());
            user.setFollow(xMapper.isHisFans(myId,c.getUserId()));
            c.setUser(user);
        }
        return list;
    }

}
