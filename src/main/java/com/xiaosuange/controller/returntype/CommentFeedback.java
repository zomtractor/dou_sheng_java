package com.xiaosuange.controller.returntype;

import com.xiaosuange.pojo.Comments;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CommentFeedback extends Feedback{
    protected Comments comment;
}
