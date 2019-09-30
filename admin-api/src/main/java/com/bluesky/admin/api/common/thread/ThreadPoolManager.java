package com.bluesky.admin.api.common.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Lijinchun
 * @Package com.wywk.member.expiredclean.common.thread.ThreadPoolManager
 * @date 2019/7/22 16:40
 */
@Slf4j
public class ThreadPoolManager {

    /**
     * 线程池维护线程的最少数量
     */
    private static final int CORE_POOL_SIZE = 20;

    /**
     * 线程池维护线程的最大数量
     */
    private static final int MAX_POOL_SIZE = 100;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private static final int KEEP_ALIVE_TIME = 10;

    /**
     * 线程池所使用的缓冲队列大小
     */
    private static final int WORK_QUEUE_SIZE = 100;

    /**
     * 线程池
     **/
    private static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(WORK_QUEUE_SIZE), new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 将构造方法访问修饰符设为私有，禁止任意实例化。
     **/
    private ThreadPoolManager() {

    }

    /**
     * 向线程池中添加任务方法
     **/
    public static void addExecuteTask(ITask task) {
        try {
            if (task != null) {
                THREAD_POOL.execute(task);
            }
        } catch (Exception e) {
            log.error("执行订单处理线程异常:{}", task.getErrorInfo());
        }
    }

    public static int getRunningTaskCount() {
        return THREAD_POOL.getActiveCount();
    }

    public static ThreadPoolExecutor getPoolExecutor() {
        return THREAD_POOL;
    }

    public static int getCorePoolSize() {
        return CORE_POOL_SIZE;
    }
}