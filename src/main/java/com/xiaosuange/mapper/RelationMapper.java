package com.xiaosuange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaosuange.pojo.Followers;
import com.xiaosuange.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RelationMapper extends BaseMapper<Followers> {

    @Select("select f.follower_id from followers f where follow_id = #{id}")
    List<Long> getFansIdList(Long id);
    @Select("select f.follow_id from followers f where follower_id = #{id}")
    List<Long> getUpsIdList(Long id);
}
