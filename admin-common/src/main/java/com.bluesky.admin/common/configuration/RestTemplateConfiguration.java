package com.bluesky.admin.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Lijinchun
 * @Package com.wywk.member.expiredclean.common.configuration
 * @date 2019/7/25 18:16
 */
@Configuration
public class RestTemplateConfiguration {
    @Bean
    public RestTemplate newRestTemplate(){
        return new RestTemplate();
    }
}
