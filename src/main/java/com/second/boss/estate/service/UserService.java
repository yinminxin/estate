package com.second.boss.estate.service;

import com.second.boss.estate.entity.User;

/**
 * @author yinminxin
 * @description 用户业务接口
 * @date 2019/12/16 16:26
 */
public interface UserService {


    /**
     * 通过用户名和密码查找
     * @param userName
     * @param password
     * @return
     */
    User findByUserNameAndPassWord(String userName, String password);

    /**
     * 根据手机号查找用户
     * @param phone
     * @return
     */
    User findByPhone(String phone);
}
