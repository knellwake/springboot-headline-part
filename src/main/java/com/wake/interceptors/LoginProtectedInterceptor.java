package com.wake.interceptors;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wake.utils.JwtHelper;
import com.wake.utils.Result;
import com.wake.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Description: 登录包含拦截器，检查请求头是否包含有效token
 * 有 有效 放行
 * 没有 无效 返回504
 */
@Component
public class LoginProtectedInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求头从获取token
        String token = request.getHeader("token");
        // 检查是否有效
        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)) {
            // 无效返回504的状态json
            Result<Object> result = Result.build(null, ResultCodeEnum.NOTLOGIN);

            // 能将result对象转为字符串
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(result);
            response.getWriter().print(json);

            return false;
        }
        // 有效放行
        return true;
    }
}