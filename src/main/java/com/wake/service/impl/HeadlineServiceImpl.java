package com.wake.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wake.pojo.Headline;
import com.wake.service.HeadlineService;
import com.wake.mapper.HeadlineMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-03-14 23:24:27
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

}




