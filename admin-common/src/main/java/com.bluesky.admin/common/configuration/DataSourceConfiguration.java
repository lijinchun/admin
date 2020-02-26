package com.bluesky.admin.common.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lijinchun
 * @Package com.bluesky.admin.api.common.configuration.DataSourceConfiguration
 * @date 2019/7/22 16:40
 */
@Configuration
@ConditionalOnProperty("spring.datasource.mult-enable")
public class DataSourceConfiguration {

    @Bean(name="db_read")
    @ConfigurationProperties(prefix="spring.datasource.db-read")
    public HikariDataSource dataSourceReadOnly() {

        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name="db_w")
    @ConfigurationProperties(prefix="spring.datasource.db-write")
    public HikariDataSource dataSourceWriteOnly() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name="db_r_w")
    @ConfigurationProperties(prefix="spring.datasource.hikari")
    public HikariDataSource dataSourceReadWrite() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }


    @Bean(name="dataSource")
    @Primary
    public DynamicDataSource  dataSource(@Qualifier("db_read")HikariDataSource dataSourceRead, @Qualifier("db_w")HikariDataSource datasourceWrite, @Qualifier("db_r_w")HikariDataSource datasourceReadWrite) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(datasourceWrite);
        Map<Object,Object> targetDataSources = new HashMap<>(8);
        targetDataSources.put(DataSourceEnum.FIRST,dataSourceRead);
        targetDataSources.put(DataSourceEnum.SECOND,datasourceWrite);
        targetDataSources.put(DataSourceEnum.THIRD,datasourceWrite);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Autowired
    private Interceptor[] plugins;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dataSource")DynamicDataSource dataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        if(plugins != null){
            factoryBean.setPlugins(plugins);
        }
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources =resolver.getResources(mapperLocations);
        factoryBean.setMapperLocations(resources);
        return factoryBean;
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
