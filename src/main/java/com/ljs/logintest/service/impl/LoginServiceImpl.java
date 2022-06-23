package com.ljs.logintest.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ljs.logintest.common.constant.Constants;
import com.ljs.logintest.common.domain.AjaxResult;
import com.ljs.logintest.common.exception.ServiceException;
import com.ljs.logintest.common.exception.user.CaptchaException;
import com.ljs.logintest.common.exception.user.CaptchaExpireException;
import com.ljs.logintest.common.exception.user.UserPasswordNotMatchException;
import com.ljs.logintest.common.redis.RedisCache;
import com.ljs.logintest.common.utils.JwtUtil;
import com.ljs.logintest.common.utils.StringUtils;
import com.ljs.logintest.mapper.UserMapper;
import com.ljs.logintest.pojo.LoginBody;
import com.ljs.logintest.pojo.LoginUser;
import com.ljs.logintest.pojo.User;
import com.ljs.logintest.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录逻辑
     * @param loginBody 用户登录对象
     * @return
     */
    @Override
    public AjaxResult login(LoginBody loginBody) {
        //校验验证码
        validateCaptcha(loginBody.getCode(),loginBody.getUuid());

        //AuthenticationManager authenticate进行用户认证
        Authentication authentication = null;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword()));
        }catch (Exception e){
            //如果认证没通过，会抛出异常
            if(e instanceof BadCredentialsException){
                throw new UserPasswordNotMatchException();
            }else {
                throw new ServiceException(e.getMessage());
            }
        }
        //如果认证通过了
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        //更新用户的最后一次登录时间
        userMapper.update(null,
                new UpdateWrapper<User>()
                        .eq("user_id",loginUser.getUser().getUserId())
                        .set("login_time",new Date()));

        String userId = loginUser.getUser().getUserId().toString();
        //生成token
        String jwt = JwtUtil.createJWT(userId);
        //将用户信息保存到redis，并设置3小时的过期时间
        redisCache.setCacheObject("login:"+userId,loginUser,3, TimeUnit.HOURS);

        return AjaxResult.success("登录成功").put(Constants.TOKEN,jwt);
    }

    /**
     * 校验验证码
     * @param code 验证码
     * @param uuid 唯一标识
     */
    public void validateCaptcha(String code,String uuid){
        String verifyKey = Constants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        //验证码过期
        if (captcha == null){
            throw new CaptchaExpireException();
        }
        //验证码错误
        if (!code.equalsIgnoreCase(captcha)){
            throw new CaptchaException();
        }
    }
}
