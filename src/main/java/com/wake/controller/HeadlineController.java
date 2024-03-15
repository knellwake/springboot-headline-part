package com.wake.controller;

import com.wake.pojo.Headline;
import com.wake.service.HeadlineService;
import com.wake.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("headline")
public class HeadlineController {

    @Autowired
    private HeadlineService headlineService;


    /**
     * 登录后才能 发布新闻
     *
     * @param headline
     * @param token
     * @return
     */
    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token) {
        Result result = headlineService.publish(headline, token);
        return result;
    }

    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(@RequestParam String hid) {
        Headline headline = headlineService.getById(hid);
        Map map = new HashMap();
        map.put("headline", headline);
        return Result.ok(map);
    }

    @PostMapping("update")
    public Result update(@RequestBody Headline headline) {
        Result result = headlineService.updateData(headline);
        return result;
    }

    @PostMapping("removeByHid")
    public Result removeByHid(@RequestParam String hid){
        headlineService.removeById(hid);
        return Result.ok(null);
    }
}