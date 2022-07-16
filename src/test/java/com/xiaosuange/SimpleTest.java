package com.xiaosuange;

import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

public class SimpleTest {
    @Test
    public void testtest(){

        String str = "zs666;,16578020099";
        System.out.println(str.matches(".*;,\\d{10}"));
    }
}
