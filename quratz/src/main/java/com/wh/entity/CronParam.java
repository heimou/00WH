package com.wh.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 时间控制器 cron
 * @author:liyujie
 * @date:2018/6/12
 */
@Data
public class CronParam implements Serializable {
    private static final long serialVersionUID = -41760386902983280L;

    private String year;
    private String week;
    private String mon;
    private String day;
    private String hour;
    private String min;
    private String sec;
    /**
     * cron 时间模版
     */
    private String dateFormat;


}
