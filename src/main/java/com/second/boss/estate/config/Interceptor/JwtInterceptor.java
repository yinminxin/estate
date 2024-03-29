package com.second.boss.estate.config.Interceptor;

import com.second.boss.estate.config.properties.JwtProperties;
import com.second.boss.estate.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//拦截器
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    private RedisUtils redisUtils;

    @Autowired
    public void init(RedisUtils redisUtils){
        this.redisUtils = redisUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL);
        //自动排除生成token的路径,并且如果是options请求是cors跨域预请求，设置allow对应头信息
        if(request.getRequestURI().endsWith("/test/login") || RequestMethod.OPTIONS.toString().equals(request.getMethod())){
            return true;
        }

        //设置allow对应头信息
//        this.allowedOrigins(request,response);

        //从请求的头字段中拿到token信息
        String token = request.getHeader("Authorization");

        //拦截请求判断cookie中的token是否通过
//        String token = CookieUtils.getCookieValue(request, jwt.getCookieName());
        if (StringUtils.isNotBlank(token) && redisUtils.hasKey(token) && redisUtils.getExpire(token) >= 0) {
            //token不为空,判断redis中token对应的value是否存在,判断redis中的用户数据是否过期
            //刷新token
            redisUtils.expire(token, 60L * 30);
            return true;
        }
        throw new Exception(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
    }


    /**
     * 处理跨域问题
     */
//    private void allowedOrigins(HttpServletRequest request, HttpServletResponse response) {
//
//        ServletContext context = request.getServletContext();
//        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
//        Cors cors = ctx.getBean(Cors.class);
//
//        String[] allowDomains = cors.getAllowedOrigins();
//        Set allowOrigins = new HashSet(Arrays.asList(allowDomains));
//        String originHeads = request.getHeader("Origin");
//        if(allowOrigins.contains(originHeads)){
//            //设置允许跨域的配置
//            // 这里填写你允许进行跨域的主机ip（生产环境可以动态配置具体允许的域名和IP）
//            response.setHeader("Access-Control-Allow-Origin", originHeads);
//            /*response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Cookie");
//            response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
//            response.setHeader("Access-Control-Allow-Credentials", "true");*/
//        }else if(allowOrigins.contains("*")){
//            //设置允许跨域的配置
//            // dev环境设置*
//            response.setHeader("Access-Control-Allow-Origin", "*");
//        }
//    }
}