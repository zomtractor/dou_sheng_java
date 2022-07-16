package com.xiaosuange.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Comments {

    @TableId(type = IdType.AUTO)
    private Long id;
    @JsonIgnore
    private Long videoId;
    @JsonIgnore
    private Long userId;
    private String content;
    @JsonProperty("create_date")
    private Long createTime;
    @TableField(exist = false)
    @JsonProperty("user")
    private Users user;


}
