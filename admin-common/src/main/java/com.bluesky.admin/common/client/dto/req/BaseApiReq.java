package com.bluesky.admin.common.client.dto.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Lijinchun
 * @Package cn.wywk.yvip.duiba.client.dto.req
 * @date 2019/7/16 10:37
 */
@Data
public abstract class BaseApiReq {
    /**
     * uid
     */
    @NotNull
    private String uid;

    /**
     * 业务id,防重
     */
    @NotNull
    private String bizId;

    @NotNull
    private String timestamp;
}
