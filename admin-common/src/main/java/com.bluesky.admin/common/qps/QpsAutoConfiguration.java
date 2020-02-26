package com.bluesky.admin.common.qps;

import com.bluesky.admin.api.common.annotation.DS;
import com.bluesky.admin.api.common.qps.annotation.Qps;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lijinchun
 * @Package com.wywk.member.expiredclean.common.configuration
 * @date 2019/7/26 13:47
 */
@Aspect
@Configuration
@ConditionalOnProperty(name = "qps.enable", havingValue = "true")
public class QpsAutoConfiguration {
    @Before("@annotation(qps)")
    public void beforeSwitchDS(Qps qps, JoinPoint joinPoint){
    }


    @After("@annotation(Qps)")
    @ConditionalOnProperty
    public void afterSwitchDS(DS Qps){
    }
}
