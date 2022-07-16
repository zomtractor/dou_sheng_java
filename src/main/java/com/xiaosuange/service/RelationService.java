package com.xiaosuange.service;

import com.xiaosuange.pojo.Users;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RelationService {


    List<Users> getFansList(Long myId, Long hisId);

    List<Users> getUpsList(Long myId, Long hisId);

    void subscribe(Long myId, Long hisId);

    void disSubscribe(Long myId, Long hisId);
}
