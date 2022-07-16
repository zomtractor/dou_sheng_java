package com.xiaosuange.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Followers {
    private Long followId;
    private Long followerId;
}
