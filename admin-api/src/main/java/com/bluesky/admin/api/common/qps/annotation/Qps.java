package com.bluesky.admin.api.common.qps.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Lijinchun
 * @Package com.wywk.member.expiredclean.common.annotation
 * @date 2019/7/26 13:44
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD
})
public @interface Qps {
    String name() default "";
}
