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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Description: 主数据配置
 * @author:liyujie
 * @date:2018/6/10
 */
@Configuration
@MapperScan(basePackages = "com.wh.dao.onedata",sqlSessionTemplateRef = "oneSqlSessionTemplate")
public class OneDataSourceConfig {

    private Logger logger = LoggerFactory.getLogger(OneDataSourceConfig.class);
    static final String MAPPER_LOCATION = "classpath:oneMapper/*.xml";
    @Value("${spring.datasource.onedata.url}")
    private String dbUrl;

    @Value("${spring.datasource.onedata.username}")
    private String username;

    @Value("${spring.datasource.onedata.password}")
    private String password;

    @Value("${spring.datasource.onedata.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.onedata.filters}")
    private String filters;


    /**
     *  SqlFactory get DataSource 配置
     *  @Primary 主数据源
     * @return
     */
    @Bean(name = "onedataSource")
    @Primary
    public DataSource onedata(){
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


    @Bean(name = "oneTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(onedata());
    }


    @Bean(name = "oneSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("onedataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources(OneDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean(name = "oneSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate oneSqlSessionTemplate(@Qualifier("oneSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
