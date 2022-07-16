package com.xiaosuange.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosuange.mapper.UserMapper;
import com.xiaosuange.mapper.XMapper;
import com.xiaosuange.pojo.Users;
import com.xiaosuange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private XMapper xMapper;

    @Override
    public Users register(String username, String password){
        Users users = userMapper.selectOne(new QueryWrapper<Users>().eq("name", username));
        if(users!=null) return null;
        long salt = System.currentTimeMillis()/1000;
        users = new Users()
                .setName(username)
                .setAvatar("http://139.224.105.6/public/avatar.jpg")
                .setBackgroundImage("http://139.224.105.6/public/background_image.jpg")
                .setSalt(""+salt)
                .setHash(DigestUtils.md5DigestAsHex((password+salt).getBytes()))
                .setSignature("记录美好生活");
        userMapper.insert(users);
        return users;
    }

    @Override
    public Users login(String username,String password){
        Users user = userMapper.selectOne(new QueryWrapper<Users>()
                .eq("name", username));
        if(user==null) return null;
        if(user.getHash().equals(DigestUtils.md5DigestAsHex((password+user.getSalt()).getBytes()))){
            return user;
        }
        return null;
    }
    @Override
    public Users getInfo(Long userId, Long myId){
        Users user = userMapper.selectById(userId);
        if(user==null) return null;
        return user.setFollow(xMapper.isHisFans(myId,userId));
    }

    @Override
    public Users getMe(String token) {
        return userMapper.selectByToken(token);
    }


}
