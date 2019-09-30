package com.bluesky.admin.api.common.configuration;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Lijinchun
 * @Package com.bluesky.admin.api.common.configuration.DynamicDataSource
 * @date 2019/7/22 16:40
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        //如果没找到数据 源则会使用默认数据源
        return DataSourceContextHolder.getDataSource();
    }

}
