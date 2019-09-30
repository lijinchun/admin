package com.bluesky.admin.api.common.client.dto.resp;

import lombok.Data;

/**
 * @author Lijinchun
 * @Package com.wywk.member.expiredclean.common.client.dto.resp
 * @date 2019/7/25 14:12
 */
@Data
public class NotifyResp<T> {
    private Integer code;
    private String message;
    private T data;
}
