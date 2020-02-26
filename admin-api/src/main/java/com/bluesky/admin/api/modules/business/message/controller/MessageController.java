package com.bluesky.admin.api.modules.business.message.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.bluesky.admin.api.common.client.ApiResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lijinchun
 * @Package com.bluesky.admin.api.modules.business.message.controller
 * @date 2020/2/26 17:33
 * @since 0.0.1
 */
@RestController
@RequestMapping("/api/message")
@Slf4j
public class MessageController {
    @NacosValue(value = "${test.nacos-name}",autoRefreshed = true)
    private String testNacosName;

    /**
     * 测试类
     * @return
     */
    @RequestMapping("/test")
    @ApiOperation(value="测试-NacosValue", notes="测试-NacosValue-notes")
    public ApiResult<?> test(){
        return ApiResult.newSuccess(testNacosName);
    }
}
