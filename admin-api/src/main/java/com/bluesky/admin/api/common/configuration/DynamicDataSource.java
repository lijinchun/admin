package com.bluesky.admin.api.common.configuration;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.sql.Connection;
import java.sql.SQLException;

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

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return super.getConnection(username, password);
    }
}
