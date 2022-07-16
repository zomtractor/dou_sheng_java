package com.xiaosuange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaosuange.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper extends BaseMapper<Users> {

    @Select("select * from users where concat(name,';,',salt)=#{token}")
    Users selectByToken(String token);
}
