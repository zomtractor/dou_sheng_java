package com.xiaosuange.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Users {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    @JsonIgnore
    private String hash;
    @JsonIgnore
    private String salt;
    @JsonProperty("video_count")
    private Long videoCount;
    @JsonProperty("follow_count")
    private Long followCount;
    @JsonProperty("follower_count")
    private Long followerCount;
    @JsonProperty("favorite_count")
    private Long favoriteCount;
    private String avatar;
    private String signature;
    @JsonProperty("background_image")
    private String backgroundImage;
    @JsonProperty("is_follow")
    @TableField(exist = false)
    private boolean isFollow;

    public Users addFollowCount() {
        ++followCount;
        return this;
    }

    public Users subFollowCount() {
        --followCount;
        return this;
    }

    public Users addFollowerCount() {
        ++followerCount;
        return this;
    }

    public Users subFollowerCount() {
        --followerCount;
        return this;
    }
    public Users addFavoriteCount() {
        ++favoriteCount;
        return this;
    }

    public Users subFavoriteCount() {
        --favoriteCount;
        return this;
    }
}

