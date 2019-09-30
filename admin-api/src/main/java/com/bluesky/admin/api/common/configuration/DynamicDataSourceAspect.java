package com.bluesky.admin.api.common.configuration;

import com.bluesky.admin.api.common.annotation.DS;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Lijinchun
 * @Package com.bluesky.admin.api.common.configuration.DynamicDataSourceAspect
 * @date 2019/7/22 16:40
 */
@Aspect
@Component
@Slf4j
public class DynamicDataSourceAspect {
    @Before("@annotation(ds)")
    public void beforeSwitchDS(DS ds){
        DataSourceContextHolder.setDataSource(ds.value());
        log.info("数据源切换为 "+ ds.value().getCode());
    }


    @After("@annotation(ds)")
    public void afterSwitchDS(DS ds){
        log.info("清除当前数据源 "+ DataSourceContextHolder.getDataSource());
        DataSourceContextHolder.clear();
    }
}
