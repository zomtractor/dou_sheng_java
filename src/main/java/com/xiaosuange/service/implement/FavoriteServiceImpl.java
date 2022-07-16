package com.xiaosuange.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosuange.mapper.FavoriteMapper;
import com.xiaosuange.mapper.UserMapper;
import com.xiaosuange.mapper.VideoMapper;
import com.xiaosuange.mapper.XMapper;
import com.xiaosuange.pojo.Favorites;
import com.xiaosuange.pojo.Users;
import com.xiaosuange.pojo.Videos;
import com.xiaosuange.service.FavoriteService;
import com.xiaosuange.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private XMapper xMapper;
    @Autowired
    private FavoriteMapper fMapper;
    @Autowired
    private VideoMapper vMapper;
    @Autowired
    private UserMapper uMapper;

    @Override
    public List<Videos> getFavoriteList(Long userId, Long myId) {
        List<Long> idList = fMapper.getFavoriteIdList(userId);
        if (idList.size() == 0) return new ArrayList<>();
        List<Videos> vList = vMapper.selectList(new QueryWrapper<Videos>().in("id", idList));
        for (Videos v : vList) {
            v.setUser(uMapper.selectById(v.getAuthorId()));
            Users user = v.getUser();
            user.setFollow(xMapper.isHisFans(myId, user.getId()));
            v.setFavorite(xMapper.isFavorite(myId, v.getId()));
        }
        return vList;
    }

    @Override
    public void like(Long uid, Long vid) {
        fMapper.insert(new Favorites().setUserId(uid).setVideoId(vid));
        uMapper.updateById(uMapper.selectById(uid).addFavoriteCount());
        vMapper.updateById(vMapper.selectById(vid).addFavoriteCount());
    }

    @Override
    public void dislike(Long uid, Long vid) {
        int i = fMapper.delete(new QueryWrapper<Favorites>().eq("user_id", uid).eq("video_id", vid));
        if(i==0) return;
        uMapper.updateById(uMapper.selectById(uid).subFavoriteCount());
        vMapper.updateById(vMapper.selectById(vid).subFavoriteCount());
    }

}
