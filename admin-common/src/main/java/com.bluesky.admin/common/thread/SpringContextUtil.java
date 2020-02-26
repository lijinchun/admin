package com.bluesky.admin.common.thread;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * @author Lijinchun
 * @Package com.wywk.member.expiredclean.common.thread.SpringContextUtil
 * @date 2019/7/22 16:40
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
	/**
	 * Spring应用上下文环境
 	 */
	private static ApplicationContext applicationContext;

	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 * 
	 * @param applicationContext
	 *            spring配置上下文对象
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		setContext(applicationContext);
	}

	private static void setContext(ApplicationContext context) {
		applicationContext = context;
	}
	
	/**
	 * 获得配置上下文对象
	 * 
	 * @return ApplicationContext 上下文对象
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取对象
	 * 
	 * @param name
	 *            对象id
	 * @return Object 实例化对象
	 * @throws BeansException
	 *             获取对象异常
	 */
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
}