package com.wh.config;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 任务管理
 * @author:liyujie
 * @date:2018/6/11
 */
@Component
public class QuartzManager {

    @Autowired
    private Scheduler scheduler;

    /**
     * 添加一个新的任务到调度中心
     * @param jobName          任务名
     * @param jobGroupName     任务组名
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param jobClass         任务的类类型
     * @param cron             时间表达式
     * @param objects          可变参数需要进行传参的值
     */
    public void addJob(String jobName,
                       String jobGroupName,
                       String triggerName,
                       String triggerGroupName,
                       Class jobClass, String cron, Object... objects) {
        try {
            //任务名，任务组，任务执行类
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            System.out.println("jobDetail.getKey:" + jobDetail.getKey());
            //触发器
            if (objects != null) {
                for (int i = 0; i < objects.length; i++) {
                    //该数据可以通过Job 中的JobDataMap dataMap=context.getJobDetail().getJobDataMap()
                    //来进行参数传递值
                    jobDetail.getJobDataMap().put("data" + (i + 1), objects[i]);
                }
            }
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            //触发器名，触发器组
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            //触发时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            //创建Trigger 对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();
            //调度容器设置JobDetail和 Trigger
            scheduler.scheduleJob(jobDetail, trigger);
            //启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            throw new RuntimeException(e);
        }

    }

    /**
     * 修改一个任务的触发时间
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     * @param cron
     */
    public void modifyJobTime(String jobName,
                              String jobGroupName,
                              String triggerName,
                              String triggerGroupName,
                              String cron) {

        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equals(cron)) {
                //触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                //触发器名，触发器组
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                //触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                //创建Trigger 对象
                trigger = (CronTrigger) triggerBuilder.build();
                //方式一： 修改一个任务的触发时间
                scheduler.rescheduleJob(triggerKey, trigger);

            }

        } catch (Exception e) {
            System.out.println("Exception:" + e);
            throw new RuntimeException(e);
        }

    }

    /**
     * 功能: 移除一个任务
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {

            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));

            System.out.println("removeJob:" + JobKey.jobKey(jobName));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 功能：启动所有定时任务
     */
    public void startJobs() {
        try {
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 功能：关闭所有定时任务
     */
    public void shutdownJobs() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
