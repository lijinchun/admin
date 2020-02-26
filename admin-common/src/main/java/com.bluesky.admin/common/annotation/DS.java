package com.bluesky.admin.common.annotation;



import com.bluesky.admin.api.common.configuration.DataSourceEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD
})
public @interface DS {
    DataSourceEnum value() default DataSourceEnum.THIRD;
}
