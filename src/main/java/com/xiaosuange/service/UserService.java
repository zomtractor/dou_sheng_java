package com.xiaosuange.service;

import com.xiaosuange.pojo.Users;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {
    Users register(String username, String password);

    Users login(String username, String password);

    Users getInfo(Long userID, Long id);

    Users getMe(String token);
}
