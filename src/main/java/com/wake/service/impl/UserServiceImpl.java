package com.wake.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wake.pojo.User;
import com.wake.service.UserService;
import com.wake.mapper.UserMapper;
import com.wake.utils.JwtHelper;
import com.wake.utils.MD5Util;
import com.wake.utils.Result;
import com.wake.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description 针对表【news_user】的数据库操作Service实现
 * @createDate 2024-03-14 23:24:27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>();
        userLambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(userLambdaQueryWrapper);

        if (loginUser == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        //对比密码
        if (!StringUtils.isEmpty(loginUser.getUserPwd())
                && MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())) {

            //根据用户ID生成token
            //并且将token封装进result返回
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            Map data = new HashMap();
            data.put("token", token);
            //成功
            return Result.ok(data);
        }

        //密码错误
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result getUserInfo(String token) {
        //判断token是否过期，TRUE到期，FALSE未到期
        if (jwtHelper.isExpiration(token)) {
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }

        int userId = jwtHelper.getUserId(token).intValue();

        User user = userMapper.selectById(userId);
        user.setUserPwd("");

        Map map = new HashMap();
        map.put("loginUser",user);

        return Result.ok(map);
    }

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername,username);

        Long count = userMapper.selectCount(userLambdaQueryWrapper);

        //为0，没有数据
        if(count == 0){
            return Result.ok(null);
        }

        return Result.build(null,ResultCodeEnum.USERNAME_USED);
    }

    /**
     * 注册
     * 1. 检查账号是否已被已被占用
     * 2. 密码加密MD5
     * 3. 账号数据保存insert
     * 4. 返回结果
     * @param user
     * @return
     */
    @Override
    public Result register(User user) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername,user.getUsername());

        Long count = userMapper.selectCount(userLambdaQueryWrapper);

        if(count > 0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));

        userMapper.insert(user);

        return Result.ok(null);
    }

}




