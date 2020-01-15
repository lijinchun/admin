package com.bluesky.admin.api.common.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.WebApplicationInitializer;

/**
 * @author 李金春
 * @Package com.bluesky.admin.api.common.configuration.SystemInitializer
 * @date 2019/6/28 18:25
 */
@Configuration
@ConditionalOnWebApplication
@Slf4j
public class SystemInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("Spring context loaded");
    }
}
