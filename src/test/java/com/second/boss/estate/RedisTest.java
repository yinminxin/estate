package com.second.boss.estate;

import com.second.boss.estate.entity.User;
import com.second.boss.estate.service.UserService;
import com.second.boss.estate.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yinminxin
 * @description redis测试类
 * @date 2019/12/17 18:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserService userService;

    @Test
    public void test(){
        User user = userService.findByPhone("18679932619");
        redisUtils.set(user.getId(),user,30L);
        User user1 = (User) redisUtils.get(user.getId());

        System.out.println(user1);
    }
}
