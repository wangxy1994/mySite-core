package com.wangxy.site.manager.filter;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT  拦截器
 */
@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("经过JwtFilter");
        //微服务鉴权

        if (request.getMethod().equals("OPTIONS")) {
            //跨域资源共享标准新增了一组 HTTP 首部字段，允许服务器声明哪些源站有权限访问哪些资源。
            // 另外，规范要求，对那些可能对服务器数据产生副作用的 HTTP 请求方法（特别是 GET 以外的 HTTP 请求，或者搭配某些 MIME 类型的 POST 请求），
            // 浏览器必须首先使用 OPTIONS 方法发起一个预检请求（preflight request），
            // 从而获知服务端是否允许该跨域请求。服务器确认允许之后，才发起实际的 HTTP 请求。
            // 在预检请求的返回中，服务器端也可以通知客户端，是否需要携带身份凭证（包括 Cookies 和 HTTP 认证相关数据）。
            // 参考：https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Access_control_CORS
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }else{
            String header = request.getHeader("Authorization");

            if( header!=null ){
                if(header.startsWith("Bearer ")){
                    String token = header.substring(7);
                    try{
                        Claims claims = jwtUtil.parseJWT(token);//获取载荷
                        if(claims!=null){
                            if(claims.get("roles").equals("admin")){//管理员身份
                                request.setAttribute("admin_claims",claims);
                            }
                            if(claims.get("roles").equals("user")){//普通用户
                                request.setAttribute("user_claims",claims);
                            }
                        }
                    }catch (Exception e){
                        throw new RuntimeException("令牌不正确！");
                    }

                }
            }
            return true;
        }
    }
}
