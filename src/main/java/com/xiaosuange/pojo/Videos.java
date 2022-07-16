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
public class Videos {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    @JsonProperty("play_url")
    private String playUrl;
    @JsonIgnore
    private Long authorId;
    @JsonProperty("cover_url")
    private String coverUrl;
    @JsonProperty("favorite_count")
    private Long favoriteCount;
    @JsonProperty("comment_count")
    private Long commentCount;
    @JsonProperty("create_time")
    private Long createTime;
    @TableField(exist = false)
    @JsonProperty("author")
    private Users user;
    @JsonProperty("is_favorite")
    @TableField(exist = false)
    private boolean isFavorite;

    public Videos addFavoriteCount() {
        ++favoriteCount;
        return this;
    }

    public Videos subFavoriteCount() {
        --favoriteCount;
        return this;
    }

    public Videos addCommentCount() {
        ++commentCount;
        return this;
    }

    public Videos subCommentCount() {
        --commentCount;
        return this;
    }
}
