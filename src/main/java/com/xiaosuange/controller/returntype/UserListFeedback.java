package com.xiaosuange.controller.returntype;

import com.xiaosuange.pojo.Users;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class UserListFeedback extends Feedback {
    protected List<Users> user_list;
}
