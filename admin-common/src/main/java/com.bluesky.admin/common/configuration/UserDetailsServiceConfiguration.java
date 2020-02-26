package com.bluesky.admin.common.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Spring security 默认登录
 *     用户名密码配置为
 *     spring.security.user.name=admin
 *     spring.security.user.password=admin1234
 *     spring.security.user.roles=admin
 * @author Lijinchun
 * @Package com.wywk.member.expiredclean.common.configuration
 * @date 2019/7/25 18:49
 */
@Configuration
@Slf4j
@ConditionalOnWebApplication
@ConditionalOnMissingClass
public class UserDetailsServiceConfiguration {

    private static final String NOOP_PD_PREFIX = "{noop}";

    private static final Pattern PD_ALGORITHM_PATTERN = Pattern.compile("^\\{.+}.*$");

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(SecurityProperties properties,
                                                                 ObjectProvider<PasswordEncoder> passwordEncoder) {
        SecurityProperties.User user = properties.getUser();
        List<String> roles = user.getRoles();
        return new InMemoryUserDetailsManager(
                User.withUsername(user.getName()).password(getOrDeducePassword(user, passwordEncoder.getIfAvailable()))
                        .roles(StringUtils.toStringArray(roles)).build());
    }

    private String getOrDeducePassword(SecurityProperties.User user, PasswordEncoder encoder) {
        String password = user.getPassword();
        if (user.isPasswordGenerated()) {
            log.info(String.format("%n%nUsing generated security password: %s%n", user.getPassword()));
        }
        if (encoder != null || PD_ALGORITHM_PATTERN.matcher(password).matches()) {
            return password;
        }
        return NOOP_PD_PREFIX + password;
    }
}
