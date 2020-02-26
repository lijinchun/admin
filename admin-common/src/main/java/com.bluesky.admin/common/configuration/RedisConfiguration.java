package com.bluesky.admin.common.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * @author Lijinchun
 * @Package com.wywk.member.expiredclean.common.configuration.RedisConfiguration
 * @date 2019/7/22 16:40
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfiguration {
	@Resource
	private RedisConnectionFactory redisConnectionFactory;
	@Bean(name = "stringValueTemplate")
	public RedisTemplate<String, String> redisTemplate() {
		StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		return template;
	}
}
