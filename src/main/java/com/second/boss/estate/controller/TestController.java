package com.second.boss.estate.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.second.boss.estate.common.base.BaseController;
import com.second.boss.estate.common.result.ResponseVO;
import com.second.boss.estate.config.properties.JwtProperties;
import com.second.boss.estate.entity.User;
import com.second.boss.estate.service.UserService;
import com.second.boss.estate.utils.CookieUtils;
import com.second.boss.estate.utils.JwtUtils;
import com.second.boss.estate.utils.RedisUtils;
import com.second.boss.estate.vo.entity.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author yinminxin
 * @description
 * @date 2019/12/11 16:54
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwt;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * @apiDefine sendSuccessList
     * @apiSuccess {Number} code 响应码;200(成功),402(未知错误)
     * @apiSuccess {String[]} data 数据
     * @apiSuccess {String} message 响应信息;接口请求success或failed返回相关信息
     * @apiSuccess {Boolean} success 是否成功;通过该字段可以判断请求是否到达.
     */

    /**
     * @apiDefine sendSuccess
     * @apiSuccess {Number} code 响应码;200(成功),402(未知错误)
     * @apiSuccess {Object} data 数据
     * @apiSuccess {String} message 响应信息;接口请求success或failed返回相关信息
     * @apiSuccess {Boolean} success 是否成功;通过该字段可以判断请求是否到达.
     */

    /**
     * @api {get} /test/index 测试api
     * @apiName index
     * @apiDescription 测试api详情
     * @apiGroup test
     * @apiParam {Number} id 标题ID
     * @apiContentType application/json
     * @apiParamExample {json} Request-Example:
    {
    "id":1
    }
     * @apiUse sendSuccess
     * @apiSuccess {Object} data 数据
     * @apiSuccess {Integer} data.id 标签ID
     * @apiSuccess {String} data.name 标签名称
     * @apiSuccess {String} data.updatedTime 更新时间
     * @apiSuccessExample {json} Success-Response:
    {
    "code": "200",
    "data": {
    "id": "1",
    "name": "语文",
    "updatedTime": 1562904503000
    },
    "message": "successed",
    "success": true
    }
     */
    @GetMapping("/index")
//    @ResponseBody
    private String testIndex(Map<String,Object> map,@RequestBody String str){
        System.out.println(str);
        JSONObject jsonObject = JSON.parseObject(str);
        String name = jsonObject.getString("name");
        map.put("name",name);
        return "index";
    }

    /**
     * @api {get} /test/login 测试登陆api
     * @apiName login
     * @apiDescription 测试登陆api详情
     * @apiGroup test
     * @apiParam {String} userName 用户名
     * @apiParam {String} password 密码
     * @apiContentType application/json
     * @apiParamExample {json} Request-Example:
    {
    "userName":"18679932619",
    "password":"123456"
    }
     * @apiUse sendSuccess
     * @apiSuccess {Object} data 数据
     * @apiSuccess {Integer} data.id 标签ID
     * @apiSuccess {String} data.userName 用户名
     * @apiSuccess {String} data.phone 手机号
     * @apiSuccess {String} data.backPhone 备用手机号
     * @apiSuccess {String} data.cardNo 身份证号码
     * @apiSuccess {String} data.name 用户姓名
     * @apiSuccess {Number} data.state 用户状态
     * @apiSuccess {String} data.createTime 创建时间
     * @apiSuccess {String} data.updatedTime 更新时间
     * @apiSuccessExample {json} Success-Response:
    {
    "code": "200",
    "data": {
    "backPhone": "18679932619",
    "cardNo": "360",
    "createTime": 1576486516000,
    "id": "383df4271fe211eaa68b6c4b9059b0aa",
    "name": "殷",
    "phone": "18679932619",
    "state": 0,
    "updateTime": 1576486516000,
    "userName": "18679932619"
    },
    "message": "successed",
    "success": true
    }
     */
    @GetMapping("/login")
    @ResponseBody
    private ResponseVO testLogin(@RequestBody String str){
        //用户Id
        String userName = null;
        String passWord = null;
        if (StringUtils.isNotBlank(str)) {
            //解析json字符串
            JSONObject jsonObject = JSON.parseObject(str);
            try {
                if(StringUtils.isNotBlank(jsonObject.getString("userName")) && StringUtils.isNotBlank(jsonObject.getString("passWord"))){
                    userName = jsonObject.getString("userName");
                    passWord = jsonObject.getString("passWord");
                }else{
                    return getFailure("参数错误");
                }
            } catch (NumberFormatException e) {
                return getFailure();
            }
        }else {
            return getFailure("参数错误");
        }
        //根据用户名和密码查找用户
        User user = userService.findByUserNameAndPassWord(userName,passWord);
        if (user == null) {
            return getFailure("用户名或密码错误!");
        }
        String token = null;
        try {
            //用户存在生成token
            token = JwtUtils.generateToken(new UserInfo(user.getId(), user.getUserName()), jwt.getPrivateKey(), jwt.getExpire());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isBlank(token)) {
            return getFailure();
        }
        //将用户信息存入redis
        redisUtils.set(token,user,60L*30);
        //将token存入cookie
//        CookieUtils.setCookie(request,response,jwt.getCookieName(),token,jwt.getCookieMaxAge(),null,true);
        user.setToken(token);
        return getFromData(user);
    }

    /**
     * @api {get} /test/loginOut 登出
     * @apiName loginOut
     * @apiDescription 登出，token过期
     * @apiGroup test
     * @apiUse sendSuccess
     * @apiSuccess {Object} data 数据
     * @apiSuccessExample {json} Success-Response:
    {
    "code": "200",
    "data": null,
    "message": "successed",
    "success": true
    }
     */
    @GetMapping("/loginOut")
    @ResponseBody
    private ResponseVO loginOut(){
//        String token = CookieUtils.getCookieValue(request, jwt.getCookieName());
        redisUtils.del(getToken());
        return getSuccess();
    }
}
