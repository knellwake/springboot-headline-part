package com.wake.service;

import com.wake.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wake.utils.Result;

/**
* @author Administrator
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-03-14 23:24:27
*/
public interface UserService extends IService<User> {

    /**
     * 登录
     * @param user
     * @return
     */
    Result login(User user);

    /**
     * 根据请求头token获取用户信息
     * @param token
     * @return
     */
    Result getUserInfo(String token);

    /**
     * 用户注册时验证用户名是否占用
     * @param username
     * @return
     */
    Result checkUserName(String username);

    /**
     * 用户注册
     * @param user
     * @return
     */
    Result register(User user);
}
