package com.wake.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wake.pojo.User;
import com.wake.service.UserService;
import com.wake.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-03-14 23:24:27
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




