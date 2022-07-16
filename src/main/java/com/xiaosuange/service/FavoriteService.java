package com.xiaosuange.service;


import com.xiaosuange.pojo.Videos;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FavoriteService {
    List<Videos> getFavoriteList(Long uid, Long vid);

    void like(Long uid, Long vid);

    void dislike(Long uid, Long vid);
}
