package com.bluesky.admin.api.modules.sys.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author Lijinchun
 * @Package com.bluesky.admin.api.modules.sys.vo
 * @date 2019/7/31 0:24
 */
public interface ModelWrap<T> {
    default T toModel(Class<T> clazz){
        Logger log = LoggerFactory.getLogger(this.getClass());
        T t;
        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(this, t);
            return t;
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    default T toModelWithBaseInfo(Class<T> clazz){
        Logger log = LoggerFactory.getLogger(this.getClass());
        T t;
        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(this, t);
            for (Field field : clazz.getDeclaredFields()) {
                String fieldName = field.getName();
                if("createdBy".equals(fieldName) || "updatedBy".equals(fieldName)){
                    if(!field.isAccessible()){
                        field.setAccessible(true);
                    }
                    field.set(t, "");
                }
                if("createdAt".equals(fieldName) || "updatedAt".equals(fieldName)){
                    if(!field.isAccessible()){
                        field.setAccessible(true);
                    }
                    field.set(t, new Date(System.currentTimeMillis()));
                }
            }
            return t;
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }
}
