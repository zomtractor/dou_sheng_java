package com.xiaosuange.controller.returntype;

import com.xiaosuange.pojo.Users;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserInfoFeedback extends Feedback {
    protected Users user;
}
