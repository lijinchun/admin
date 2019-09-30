package com.bluesky.admin.api.common.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Lijinchun
 * @Package com.wywk.member.expiredclean.common.configuration.SecurityPermitAllConfiguration
 * @date 2019/7/22 16:40
 */
@Configuration
public class SecurityPermitAllConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and().formLogin()
                .and().httpBasic().and()
                .cors().disable()
                .csrf().disable();
    }
}
