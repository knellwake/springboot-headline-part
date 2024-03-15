package com.wake.pojo.vo;

import lombok.Data;

@Data
public class PortalVo {

    private String keyWords;
    private Integer type;
    private Integer pageNum = 1;
    private Integer pageSize =10;
}