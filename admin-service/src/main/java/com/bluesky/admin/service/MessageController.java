package com.bluesky.admin.service;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.bluesky.admin.common.client.ApiResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李金春
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/msg")
public class MessageController {
    @NacosValue(value = "${config.test-msg:''}",autoRefreshed = true)
    private String testMsg;

    @NacosInjected
    private NamingService namingService;

    /**
     *
     * @return
     */
    @RequestMapping("/testMsg")
    @ApiOperation(notes = "notes", value = "testMsg")
    public ApiResult<?> testMsg(String serviceName){
        try {
            return ApiResult.newSuccess(namingService.getAllInstances(serviceName));
        } catch (NacosException e) {
            log.error("", e);
        }
        return ApiResult.newSuccess(testMsg);
    }
}
