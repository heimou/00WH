package com.wh.utils;

import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 定时任务 时间格式转换
 * @author:liyujie
 * @date:2018/6/11
 */
public class QuartzCronDateUtils {

    private final static Logger logger = LoggerFactory.getLogger(QuartzCronDateUtils.class);

    /**
     * 日期转换cron 表达式时间格式
     *
     * @param date
     * @param dateFormat
     * @return ss mm HH dd MM ? yyyy
     */
    public static String formatDataByPatter(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    /**
     * convert Date to cron
     *
     * @param date
     * @return
     */
    public static String getCron(Date date) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return formatDataByPatter(date, dateFormat);
    }


    public static void main(String[] args) {
        //固定时间 data -->转成cron 表达式
        System.out.println(">>>>>>>>>>>>>>>>" + getCron(new Date()));
        // 不固定时间格式转换 例如 每隔多少秒转换
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("myJob").
                startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(60).repeatForever()).build();
    }

    /**
     * 时间格式 可以选择的情况
     * 1  具体的时间  ss mm HH dd MM 1-7 YYYY  1-7 代表
     * 2  不具体的时间  需要判断是否有值 可以通过替换 动态增加 dateFormat
     *   Cron 表达式 具体用 Simplate
     */

}
