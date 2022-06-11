package com.ap.electivesystem.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/*
 * 开启Redis Session缓存
 */
@Configuration
@EnableCaching
@EnableRedisHttpSession
public class RedisSessionConfig {

}
