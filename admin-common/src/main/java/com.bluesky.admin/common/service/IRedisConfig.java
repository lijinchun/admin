package com.bluesky.admin.common.service;

/**
 * 快速度添加缓存配置，分布到具体功能实现，由系统功能去调用
 * @author Lijinchun
 * @Package com.bluesky.admin.api.util
 * @date 2019/7/29 17:10
 */
public interface IRedisConfig {
    boolean initConfig(String key, String value);
}
