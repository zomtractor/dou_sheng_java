package com.xiaosuange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaosuange.pojo.Favorites;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FavoriteMapper extends BaseMapper<Favorites> {
    @Select("select video_id from favorites f where user_id = #{id}")
    List<Long> getFavoriteIdList(Long id);
}
