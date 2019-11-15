package com.bluesky.admin.api.common.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.ConditionalOnMissingFilterBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author Lijinchun
 * @Package com.bluesky.admin.api.common.configuration
 * @date 2019/11/15 18:25
 * @since 0.0.1
 */
@Configuration
@ConditionalOnProperty(value = "mybatis.db.plugin.separation.sql.enable",havingValue = "true")
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
}
)
@Slf4j
public class DbSqlSeparationInterceptor implements Interceptor {
    /**
     * 排除插入ID查询
     */
    private static final String EXCLUDE_INSERT_ID = "SELECT LAST_INSERT_ID()";
    private static final String SELECT_PREFIX = "SELECT";
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 拦截sql
        Object[] args = invocation.getArgs();
        MappedStatement statement = (MappedStatement) args[0];
        Object parameterObject = args[1];
        BoundSql boundSql = statement.getBoundSql(parameterObject);
        String sql = boundSql.getSql();
        if(sql != null && !EXCLUDE_INSERT_ID.equals(sql) && sql.startsWith(SELECT_PREFIX)){
            DataSourceContextHolder.setDataSource(DataSourceEnum.FIRST);
            log.info("数据源切换为 {} ", DataSourceEnum.FIRST.getCode());
            log.debug("数据源切换,SQL：{}", sql);
            try{
                return invocation.proceed();
            }finally {
                log.info("清除当前数据源 {}", DataSourceContextHolder.getDataSource());
                DataSourceContextHolder.clear();
            }
        }else{
            return invocation.proceed();
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
