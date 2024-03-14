package com.wake.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wake.pojo.Type;
import com.wake.service.TypeService;
import com.wake.mapper.TypeMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-03-14 23:24:27
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

}




