package com.xiaosuange.pojo;


import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Favorites {
    private Long userId;
    private Long videoId;

}
