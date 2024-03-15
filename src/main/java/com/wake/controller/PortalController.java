package com.wake.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wake.pojo.vo.PortalVo;
import com.wake.service.HeadlineService;
import com.wake.service.TypeService;
import com.wake.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("portal")
public class PortalController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;



    @GetMapping("findAllTypes")
    public Result findAllTypes(){
       Result result = typeService.findAllTypes();
       return result;
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        Result result = headlineService.findNewsPage(portalVo);
        return result;
    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(@RequestParam String hid){
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }
}