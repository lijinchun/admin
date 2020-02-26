package com.bluesky.admin.common.client;

import lombok.Data;

/**
 * @author Lijinchun
 * @Package cn.wywk.yvip.duiba.client.dto
 * @date 2019/7/15 20:45
 */
@Data
public class ApiResult<T> {
    private boolean success;
    private String errorCode;
    private T data;
    private String errorMsg;

    private ApiResult(boolean success, String errorCode, T data, String errorMsg) {
        this.success = success;
        this.errorCode = errorCode;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public static <K> ApiResult<K> newFail(String errorMsg, K data){
        return new ApiResult<>(false, null, data, errorMsg);
    }

    public static ApiResult newFail(String errorMsg){
        return new ApiResult<>(false, null, null, errorMsg);
    }
    public static ApiResult newFail(String errorCode, String errorMsg){
        return new ApiResult<>(false, errorCode, null, errorMsg);
    }

    public static <T> ApiResult<T> newSuccess(T data){
        return new ApiResult<>(true, null, data, null);
    }

    public static <T> ApiResult<T> newSuccess(){
        return new ApiResult<>(true, null, null, null);
    }
}
