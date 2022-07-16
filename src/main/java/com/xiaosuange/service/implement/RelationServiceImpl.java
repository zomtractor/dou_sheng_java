package com.xiaosuange.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosuange.mapper.RelationMapper;
import com.xiaosuange.mapper.UserMapper;
import com.xiaosuange.mapper.XMapper;
import com.xiaosuange.pojo.Followers;
import com.xiaosuange.pojo.Users;
import com.xiaosuange.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RelationServiceImpl implements RelationService {
    @Autowired
    private RelationMapper rMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private XMapper xMapper;

    @Override
    public List<Users> getFansList(Long myId, Long hisId){
        List<Long> idList = rMapper.getFansIdList(hisId);
        if(idList.size()==0) return new ArrayList<>();
        List<Users> userList = userMapper.selectList(new QueryWrapper<Users>().in("id",idList));
        for(Users u:userList) u.setFollow(xMapper.isHisFans(myId,hisId));
        return userList;
    }
    @Override
    public List<Users> getUpsList(Long myId, Long hisId){
        List<Long> idList = rMapper.getUpsIdList(hisId);
        if(idList.size()==0) return new ArrayList<>();
        List<Users> userList = userMapper.selectList(new QueryWrapper<Users>().in("id",idList));
        for(Users u:userList) u.setFollow(xMapper.isHisFans(myId,hisId));
        return userList;
    }
    @Override
    public void subscribe(Long myId, Long hisId){
        rMapper.insert(new Followers().setFollowId(myId).setFollowerId(hisId));
        userMapper.updateById(userMapper.selectById(myId).addFollowCount());
        userMapper.updateById(userMapper.selectById(hisId).addFollowerCount());
    }
    @Override
    public void disSubscribe(Long myId, Long hisId) {
        int status=rMapper.delete(new QueryWrapper<Followers>().eq("follow_id",myId).eq("follower_id",hisId));
        if(status==0) return;
        userMapper.updateById(userMapper.selectById(myId).subFollowCount());
        userMapper.updateById(userMapper.selectById(hisId).subFollowerCount());
    }
}
