package com.second.boss.estate.repository;

import com.second.boss.estate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author yinminxin
 * @description 用户Repository
 * @date 2019/12/16 16:27
 */
@Repository
public interface UserRepository extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

    /**
     * 通过用户名和密码查找
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    User findByUserNameAndPassWord(String userName, String password);

    /**
     * 根据手机号查找用户
     * @param phone 手机号
     * @return
     */
    User findByPhone(String phone);
}
