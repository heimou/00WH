package com.wh.shiroConfig;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Description: 第二数据源配置  具体要某个包下
 * @author:liyujie
 * @date:2018/6/10
 */
@Configuration
@MapperScan(basePackages = "com.wh.dao.twodata", sqlSessionTemplateRef = "twoSqlSessionTemplate")
public class TwoDataSourceConfig {

    private Logger logger = LoggerFactory.getLogger(TwoDataSourceConfig.class);

    static final String MAPPER_LOCATION = "classpath:twoMapper/*.xml";

    @Value("${spring.datasource.twodata.url}")
    private String dbUrl;

    @Value("${spring.datasource.twodata.username}")
    private String username;

    @Value("${spring.datasource.twodata.password}")
    private String password;

    @Value("${spring.datasource.twodata.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.twodata.filters}")
    private String filters;


    /**
     * SqlFactory get DataSource 配置
     *
     * @return
     * @Primary 主数据源
     */
    @Bean(name = "twodataSource")
    public DataSource twodata() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid two configuration initialization filter", e);
        }
        return datasource;
    }

    @Bean(name = "twoTransactionManager")
    public DataSourceTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(twodata());
    }

    @Bean(name = "twoSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("twodataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(TwoDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean(name = "twoSqlSessionTemplate")
    public SqlSessionTemplate twoSqlSessionTemplate(@Qualifier("twoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

