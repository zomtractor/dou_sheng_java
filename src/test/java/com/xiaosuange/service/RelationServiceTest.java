package com.xiaosuange.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosuange.mapper.RelationMapper;
import com.xiaosuange.mapper.XMapper;
import com.xiaosuange.pojo.Followers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RelationServiceTest {
    @Autowired
    private RelationService rService;
    @Autowired
    private RelationMapper mapper;
    @Autowired
    private XMapper xMapper;

    @Test
    public void isHisFanTest(){
        System.out.println(xMapper.isHisFans(1l, 24l));
        System.out.println(xMapper.isHisFans(1l, 23l));
    }
    @Test
    public void testtest(){
        QueryWrapper<Followers> sq = new QueryWrapper<Followers>()
                .eq("follow_id",47)
                .eq("follower_id",46);
        System.out.println(mapper.delete(sq));
        System.out.println(mapper.delete(sq));
    }

}
