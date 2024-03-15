package com.wake.service;

import com.wake.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wake.utils.Result;

/**
* @author Administrator
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-03-14 23:24:27
*/
public interface TypeService extends IService<Type> {

    /**
     * 查询首页分类
     * @return
     */
    Result findAllTypes();
}
