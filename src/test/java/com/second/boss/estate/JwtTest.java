package com.second.boss.estate;

import com.second.boss.estate.utils.JwtUtils;
import com.second.boss.estate.utils.RsaUtils;
import com.second.boss.estate.vo.entity.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author yinminxin
 * @description
 * @date 2019/12/16 15:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {

    private static final String pubKeyPath = "D:\\rsa\\estate\\rsa.pub";

    private static final String priKeyPath = "D:\\rsa\\estate\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo("ceshi", "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6ImNlc2hpIiwidXNlcm5hbWUiOiJqYWNrIiwiZXhwIjoxNTc2NDgzOTk0fQ.Ja3GcFS_uSBYlVYugRQ1fQLBW6zqrvriODO6x2GLRN2sSO3v6D7OKOrkBE4M_mh8qgwlIFcCKctpIkRDY4oTecY_7CaY6H1_djpg80OlEekqI96jF6YaMYLgWzxR2fP5ggL3uQIkPYXM0DYiSga9Idkhn-JN3OHHA6jG-Q_Au0M";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUserName());
    }
}
