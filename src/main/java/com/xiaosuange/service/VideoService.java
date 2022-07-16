package com.xiaosuange.service;

import com.xiaosuange.pojo.Videos;

import java.io.InputStream;
import java.util.List;

public interface VideoService {
    List<Videos> getFeed(Long latest, Long userId);

    boolean saveVideo(Long userId, String title, InputStream is);

    List<Videos> getPublishList(Long userId, Long myId);
}
