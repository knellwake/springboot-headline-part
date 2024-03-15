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
}
