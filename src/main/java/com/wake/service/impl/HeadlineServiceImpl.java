package com.wake.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wake.pojo.Headline;
import com.wake.pojo.vo.PortalVo;
import com.wake.service.HeadlineService;
import com.wake.mapper.HeadlineMapper;
import com.wake.utils.JwtHelper;
import com.wake.utils.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description 针对表【news_headline】的数据库操作Service实现
 * @createDate 2024-03-14 23:24:27
 */
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
        implements HeadlineService {

    @Autowired
    private HeadlineMapper headlineMapper;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 首页数据查询
     * 1. 进行分页数据查询
     * 2. 分页数据，拼接到result即可
     * <p>
     * 注意1 ： 查询需要自定义语句，自定义Mapper的方法，携带分页
     * 注意2 ： 返回结果List<Map>
     * <p>
     * 第一层pageInfo 由service层 IPage<Headline>整理完包装成pageInfoMap 返回给controller层
     * 第二层的pageData 自定义查询语句，包装成List<Headline>
     *
     * @param portalVo
     * @return
     */
    @Override
    public Result findNewsPage(PortalVo portalVo) {
        //配置分页参数，当前页码数和当前页多少条
        IPage<Headline> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());
        //自定义查询语句
        headlineMapper.selectMyPage(page, portalVo);

        Map pageInfo = new HashMap();
        List<Headline> records = page.getRecords();
        pageInfo.put("pageData", records);
        pageInfo.put("pageNum", page.getCurrent());
        pageInfo.put("pageSize", page.getSize());
        pageInfo.put("totalPage", page.getPages());
        pageInfo.put("totalSize", page.getTotal());

        Map pageInfoMap = new HashMap();
        pageInfoMap.put("pageInfo", pageInfo);
        return Result.ok(pageInfoMap);
    }

    /**
     * 根据id查询详情
     * 1. 查询对应的数据即可【多表查询，头条和用户表，自定义Mapper方法 返回map】
     * 2. 修改阅读量 【 "pageViews":"40",   // 新闻浏览量; 涉及到乐观锁需要当前数据对应版本】
     *
     * @param hid
     * @return
     */
    @Override
    public Result showHeadlineDetail(String hid) {
        Map data = headlineMapper.selectDetailByHidMap(hid);

        HashMap headlineMap = new HashMap();
        headlineMap.put("headline", data);

        // 修改阅读量+1,version乐观锁
        Headline headline = new Headline();
        headline.setHid((Integer) data.get("hid"));
        headline.setVersion((Integer) data.get("version"));
        headline.setPageViews((Integer) data.get("pageViews") + 1);

        headlineMapper.updateById(headline);

        return Result.ok(headlineMap);
    }

    @Override
    public Result publish(Headline headline, String token) {
        // token 查询用户id
        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());

        headlineMapper.insert(headline);

        return Result.ok(null);
    }

    /**
     * 修改头条新闻
     * 1. hid查询数据的最新version
     * 2. 修改数据的更新时间为当前节点
     * @param headline
     * @return
     */
    @Override
    public Result updateData(Headline headline) {
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();

        //乐观锁
        headline.setVersion(version);
        headline.setUpdateTime(new Date());

        headlineMapper.updateById(headline);

        return Result.ok(null);
    }

    //@Override
    //public Result findHeadlineByHid(String hid) {
    //    Headline headline = headlineMapper.selectById(hid);
    //    Map map = new HashMap();
    //    map.put("headline",headline);
    //    return Result.ok(map);
    //}
}




