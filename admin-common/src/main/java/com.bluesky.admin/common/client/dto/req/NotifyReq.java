package com.bluesky.admin.common.client.dto.req;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Lijinchun
 * @Package com.wywk.member.expiredclean.common.client.dto.req
 * @date 2019/7/25 14:14
 */
@Data
public class NotifyReq implements BaseAppAipReq{

    /**
     *  非必须,默认全平台推送,推送平台:ALL/IOS/ANDROID
     */
    private String platform;
    /**
     * 模板ID
     */
    private String templateId;

    private List<String> users;

    /** 不同模板参数 */
    private Map<String, Object> androidData;
    private Map<String, Object> iosData;
}
