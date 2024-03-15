package com.wake.service;

import com.wake.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wake.pojo.vo.PortalVo;
import com.wake.utils.Result;

/**
* @author Administrator
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-03-14 23:24:27
*/
public interface HeadlineService extends IService<Headline> {

    /**
     * 分页查询首页头条信息
     * @param portalVo
     * @return
     */
    Result findNewsPage(PortalVo portalVo);

    /**
     * 查询头条详情
     * @param hid
     * @return
     */
    Result showHeadlineDetail(String hid);

    /**
     * 创建发布新闻
     * @param headline
     * @param token
     * @return
     */
    Result publish(Headline headline, String token);

    /**
     * 修改新闻
     * @param headline
     * @return
     */
    Result updateData(Headline headline);

    /**
     * 修改头条回显
     * @param hid
     * @return
     */
    //Result findHeadlineByHid(String hid);
}
