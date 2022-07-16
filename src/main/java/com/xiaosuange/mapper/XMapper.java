package com.xiaosuange.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosuange.pojo.Favorites;
import com.xiaosuange.pojo.Followers;
import com.xiaosuange.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class XMapper {
    @Autowired
    private UserMapper uMapper;
    @Autowired
    private VideoMapper vMapper;
    @Autowired
    private RelationMapper rMapper;
    @Autowired
    private FavoriteMapper fMapper;

    public boolean isHisFans(Long myId, Long hisId){
        return rMapper.selectOne(new QueryWrapper<Followers>()
                .eq("follow_id",myId)
                .eq("follower_id",hisId))!=null;
    }
    public boolean isFavorite(Long uid, Long vid) {
        return fMapper.selectOne(new QueryWrapper<Favorites>()
                .eq("user_id", uid)
                .eq("video_id", vid)) != null;
    }
    public Users getMe(String token) {
        return uMapper.selectByToken(token);
    }


}
