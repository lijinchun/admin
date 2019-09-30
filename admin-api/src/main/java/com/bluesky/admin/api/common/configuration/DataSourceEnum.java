package com.bluesky.admin.api.common.configuration;

/**
 * @author Lijinchun
 * @Package com.bluesky.admin.api.common.configuration.DataSourceEnum
 * @date 2019/7/22 16:40
 */
public enum DataSourceEnum {
    /**
     * 只读库
     */
    FIRST("FIRST"),
    /**
     * 只写库
     */
    SECOND("SECOND"),
    /**
     * 默认库
     */
    THIRD("THIRD");
    String code;
    DataSourceEnum(String code){
        this.code =code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
