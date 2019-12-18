package com.second.boss.estate.service.impl;

import com.second.boss.estate.config.properties.JwtProperties;
import com.second.boss.estate.entity.User;
import com.second.boss.estate.repository.UserRepository;
import com.second.boss.estate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yinminxin
 * @description 用户业务实现类
 * @date 2019/12/16 16:26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProperties jwt;

    @Override
    public User findByUserNameAndPassWord(String userName, String password) {
        return userRepository.findByUserNameAndPassWord(userName,password);
    }

    @Override
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }
}
