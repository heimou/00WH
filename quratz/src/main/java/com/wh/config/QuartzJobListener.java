package com.wh.config;

import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 定时任务监听
 * Quartz 的job 在任务重启的时候 job 都失效了，把job 都存在数据库中
 * 然后项目启动时监听器读取数据库的job 然后添加job
 * @author:liyujie
 * @date:2018/6/11
 */
public class QuartzJobListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        /***处理获取数据库的job表，然后遍历循环每个加到job中 ***/
        QuartzManager quartzManager = WebApplicationContextUtils.
                getWebApplicationContext(servletContextEvent.getServletContext()).getBean(QuartzManager.class);

        //此处就不写获取数据库了，模拟一个集合遍历的数据
        List<Map<String, Object>> listMap = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("jobClass", "com.yj.quartzjob.QuartzJob");
        map1.put("jobName", "job1");
        map1.put("jobGroupName", "job1");
        map1.put("jobTime", "0/5 * * * * ? ");
        listMap.add(map1);

        for (Map<String, Object> map : listMap) {
            try {
                //调取quartzManager 的addJob 添加到任务中
//                quartzManager.addJob((String)map.get("jobName"),(String)map.get("jobGroupName"),);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("QuartzJobListener 启动了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
