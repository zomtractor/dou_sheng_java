package com.xiaosuange.controller.returntype;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserLRFeedback extends Feedback {
    protected Long user_id;
    protected String token;
}
