package com.ljs.logintest.common.handler;

import com.alibaba.fastjson.JSON;
import com.ljs.logintest.common.constant.HttpStatus;
import com.ljs.logintest.common.domain.AjaxResult;
import com.ljs.logintest.common.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理类 返回未授权
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        int code = HttpStatus.UNAUTHORIZED;

        AjaxResult ajaxResult = AjaxResult.error(code, "用户认证失败请查询登录");

        String resultJson = JSON.toJSONString(ajaxResult);
        WebUtils.renderString(response,resultJson);
    }
}
