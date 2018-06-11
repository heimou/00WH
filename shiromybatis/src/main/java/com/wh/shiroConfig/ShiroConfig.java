package com.wh.shiroConfig;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: shiro Config Fiter
 * @author:liyujie
 * @date:2018/6/11
 */
@Configuration
public class ShiroConfig {


    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "shiroRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public ShiroRealm shiroRealm() {
        return new ShiroRealm();
    }

    @Bean(name = "ehCacheManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        return ehCacheManager;
    }

    /**
     *  授权信息 存放在EhCache 中
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setCacheManager(ehCacheManager());//用户授权/认证信息Cache, 采用EhCache 缓存
        return securityManager;
    }

    /**
     *文件拦截器配置
     * @param securityManager
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<>();
        filterChainDefinitionManager.put("/logout", "logout");
        filterChainDefinitionManager.put("/user/**", "authc,roles[user]");
        filterChainDefinitionManager.put("/shop/**", "authc,roles[shop]");
        filterChainDefinitionManager.put("/admin/**", "authc,roles[admin]");
        filterChainDefinitionManager.put("/login", "anon");//anon 可以理解为不拦截
        filterChainDefinitionManager.put("/validata", "anon");//anon 可以理解为不拦截
        filterChainDefinitionManager.put("/statistic/**", "anon");//静态资源不拦截
        filterChainDefinitionManager.put("/**", "authc,roles[user]");//其他资源全部拦截
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);

        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        return shiroFilterFactoryBean;

    }

    /**
     *  开启shiro  异常代理
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator daap=new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }
}
