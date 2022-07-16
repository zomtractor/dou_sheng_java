package com.xiaosuange.controller.returntype;

import com.xiaosuange.pojo.Comments;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class CommentListFeedback extends Feedback{
    protected List<Comments> comment_list;
}
