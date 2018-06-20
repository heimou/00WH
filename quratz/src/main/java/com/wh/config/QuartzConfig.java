package com.wh.config;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @Description: 定时任务配置类
 * @author:liyujie
 * @date:2018/6/11
 */
@Configuration
public class QuartzConfig {
    @Autowired
    private MyJobFactory myJobFactory;

    /**
     * 任务调度工作
     *
     * @return
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(myJobFactory);
        System.out.println("myJobFactory:" + myJobFactory);
        return schedulerFactoryBean;
    }

    /**
     * 获取一个调度任务
     *
     * @return
     */
    @Bean
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }


}
