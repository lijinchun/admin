package com.bluesky.admin.api.common.plugins;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author Lijinchun
 * @Package com.bluesky.admin.api.common.plugins
 * @date 2020/1/13 23:41
 */
@Component
public class DefaultPlugins implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
