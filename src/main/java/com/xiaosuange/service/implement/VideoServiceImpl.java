package com.xiaosuange.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosuange.mapper.FavoriteMapper;
import com.xiaosuange.mapper.UserMapper;
import com.xiaosuange.mapper.VideoMapper;
import com.xiaosuange.mapper.XMapper;
import com.xiaosuange.pojo.Favorites;
import com.xiaosuange.pojo.Users;
import com.xiaosuange.pojo.Videos;
import com.xiaosuange.service.VideoService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private XMapper xMapper;
    @Autowired
    private VideoMapper vMapper;
    @Autowired
    private UserMapper uMapper;
    @Autowired
    private FavoriteMapper fMapper;
    @Value("${url_pf}")
    private String url_pf;
    @Value("${path_pf}")
    private String path_pf;

    @Override
    public List<Videos> getFeed(Long latest, Long myId) {
        List<Videos> ls = vMapper.selectList(new QueryWrapper<Videos>().orderByDesc("create_time"));
        for (Videos v : ls) {
            v.setUser(uMapper.selectById(v.getAuthorId()));
            Users user = v.getUser();
            user.setFollow(xMapper.isHisFans(myId,user.getId()));
            v.setFavorite(xMapper.isFavorite(myId, v.getId()));
        }
        return ls;
    }

    @Override
    public boolean saveVideo(Long userId, String title, InputStream is) {
        try {
            Videos video = new Videos().setCreateTime(System.currentTimeMillis()/1000);
            vMapper.insert(video);
            video.setAuthorId(userId)
                    .setTitle(title)
                    .setPlayUrl(url_pf + video.getId() + ".mp4")
                    .setCoverUrl(url_pf + "bk.jpg");
            vMapper.updateById(video);
            FileOutputStream fos = new FileOutputStream(path_pf + video.getId() + ".mp4");
            IOUtils.copy(is, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public List<Videos> getPublishList(Long userId, Long myId){
        List<Videos> ls = vMapper.selectList(new QueryWrapper<Videos>().eq("author_id",userId));
        for (Videos v : ls) {
            v.setUser(uMapper.selectById(userId));
            Users user = v.getUser();
            user.setFollow(xMapper.isHisFans(myId,userId));
            v.setFavorite(xMapper.isFavorite(myId, userId));
        }
        return ls;
    }
}
