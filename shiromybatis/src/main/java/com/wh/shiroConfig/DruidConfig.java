package com.wh.shiroConfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Description: DruidConfig 连接池配置
 * @author:liyujie
 * @date:2018/6/8
 */

@Configuration
public class DruidConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.filters}")
    private String filters;
    /**
     * 连接池监控界面 配置
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", "admin");
        reg.addInitParameter("loginPassword", "admin");
        return reg;
    }

    /**
     * 连接池监控过滤配置
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
        return filterRegistrationBean;
    }




    /**
     *  SqlFactory get DataSource 配置
     *  @Primary 主数据源
     * @return
     */
    @Bean
    public DataSource duridDataSource(){
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid one configuration initialization filter", e);
        }
        return datasource;
    }

}


