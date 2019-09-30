package com.bluesky.admin.api.common.thread;

/**
 * @author Lijinchun
 * @Package com.wywk.member.expiredclean.common.thread
 * @date 2019/7/22 17:42
 */
public interface ITask extends Runnable{
    /**
     * 任务异常信息
     * @return
     */
    default String getErrorInfo(){
        return this.getClass().getName();
    }
}
