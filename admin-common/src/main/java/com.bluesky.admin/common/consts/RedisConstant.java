package com.bluesky.admin.common.consts;

/**
 * @author Lijinchun
 * @Package com.wywk.member.expiredclean.common.consts.RedisConstant
 * @date 2019/7/22 16:40
 */
public final class RedisConstant {
    private RedisConstant(){}
    /**
     * 定时任务相关
     */
    public static final class Task extends Base{
        /**
         * 任务锁
         */
        public static final String TASK_LOCK_KEY="TASK:LOCK_KEY:";

        /**
         * 任务锁超时间（秒）
         */
        public static final long LOCK_TIMEOUT = 600;
    }

    /**
     * 业务处理防重处理
     */
    public static final class Business extends Base{
        /**
         *业务锁
         */
        public static final String LOCK_KEY_PREFIX ="BUSINESS:LOCK_KEY:";

        /**
         * 任务锁超时间（秒）
         */
        public static final long LOCK_TIMEOUT = 600;
    }

    /**
     * 网鱼APP服务系统接口配置
     */
    public static final class AppSys extends Base{
        /**
         * 接口AIP
         */
        public static final String CFG_BASE_API_URL_KEY ="EXPIRED_CLEAN:APP_SYS:CFG:BASE_API_URL";
        public static final String CFG_BASE_API_MD5_SALT_KEY ="EXPIRED_CLEAN:APP_SYS:CFG:MD5_SALT";
        public static final String CFG_BASE_API_TEMPLATE_ID_KEY ="EXPIRED_CLEAN:APP_SYS:CFG:TEMPLATE_ID";

        /**
         * 任务锁超时间（秒）
         */
        public static final long LOCK_TIMEOUT = 600;
    }

    private static class Base{
        private Base(){}
    }
}
