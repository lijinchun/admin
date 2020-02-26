package com.bluesky.admin.api;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 李金春
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.bluesky.admin.api.modules.sys.mapper"}, annotationClass = Repository.class)
@NacosPropertySource(dataId = "admin-api", autoRefreshed = true, groupId = "admin")
public class AdminApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApiApplication.class, args);
    }

}
