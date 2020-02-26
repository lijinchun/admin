package com.bluesky.admin.common.controller;

import com.bluesky.admin.common.client.ApiResult;
import com.bluesky.admin.common.client.exception.ApiClientException;
import com.bluesky.admin.common.exception.ServiceException;
import com.bluesky.admin.common.utils.typeeditors.DateTypeEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * @author Lijinchun
 * @Package com.bluesky.admin.api.common.controller.BaseController
 * @date 2019/7/22 16:40
 */
@ApiIgnore
public abstract class BaseController {
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new DateTypeEditor());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ApiResult handlerServiceException(ServiceException e){
        return ApiResult.newFail(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(ApiClientException.class)
    @ResponseBody
    public ApiResult handlerApiClientException(ApiClientException e){
        return ApiResult.newFail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResult handlerException(Exception e){
        return ApiResult.newFail(e.getLocalizedMessage());
    }


}
