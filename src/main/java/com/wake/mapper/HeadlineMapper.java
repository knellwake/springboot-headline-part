package com.wake.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wake.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wake.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-03-14 23:24:27
* @Entity com.wake.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    /**
     * 定义分页查询方法,返回map格式数据
     * @param page
     * @param portalVo
     * @return
     */
    IPage<Headline> selectMyPage(IPage page, @Param("portalVo") PortalVo portalVo);

    /**
     * 多表查询，新闻详情
     * @param hid
     * @return
     */
    Map selectDetailByHidMap(String hid);
}




