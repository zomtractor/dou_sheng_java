package com.xiaosuange.controller.returntype;

import com.xiaosuange.pojo.Videos;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class VideoListFeedback extends Feedback {
    protected List<Videos> video_list;
}
