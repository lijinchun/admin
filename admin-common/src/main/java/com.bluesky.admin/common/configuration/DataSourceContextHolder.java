package com.bluesky.admin.common.configuration;

/**
 * @author Lijinchun
 * @Package com.bluesky.admin.api.common.configuration.DataSourceContextHolder
 * @date 2019/7/22 16:40
 */
public class DataSourceContextHolder {
    private DataSourceContextHolder(){}
    private static final ThreadLocal<DataSourceEnum> CONTEXT = new ThreadLocal<>();
    public static void setDataSource(DataSourceEnum dataSourceEnum){
        CONTEXT.set(dataSourceEnum);
    }
    public static final DataSourceEnum getDataSource(){
        return CONTEXT.get();
    }
    public static void clear(){
        CONTEXT.remove();
    }
}
