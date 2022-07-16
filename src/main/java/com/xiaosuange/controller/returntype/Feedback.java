package com.xiaosuange.controller.returntype;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
public class Feedback {
    public static final Integer STATUS_OK = 0;
    public static final Integer GET_ERR = 1;

    protected Integer status_code;
    protected String status_msg;
}
