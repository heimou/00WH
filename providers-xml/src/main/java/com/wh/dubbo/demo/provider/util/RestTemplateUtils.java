package com.wh.dubbo.demo.provider.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * @Description: RestTemplate 公共方法
 * @author:liyujie
 * @date:2018/5/31
 */
@Component
public class RestTemplateUtils {

    @Autowired
    RestTemplate restTemplate;

    private final static Logger logger = LoggerFactory.getLogger(RestTemplateUtils.class);

    /**
     *
     * @param url 访问地址
     * @param request 请求参数
     * @param responseType 返回类型
     * @param <T>
     * @param <K>
     * @return 返回ResponseEntity
     */
    public <T, K> K doPost(String url, T request, ParameterizedTypeReference<K> responseType) {
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept-Charset", "UTF-8");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> httpEntity = new HttpEntity<T>(request, headers);
        K body = null;
        try {
            ResponseEntity<K> exchange = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, responseType);
            if (exchange != null && exchange.getBody() != null) {
                body = exchange.getBody();
            }
        } catch (Exception e) {
            logger.error("调用失败" + url, e);
        }
        return body;
    }


}
