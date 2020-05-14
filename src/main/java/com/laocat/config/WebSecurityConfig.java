package com.laocat.config;

import com.laocat.handler.BcAccessDeniedHandler;
import com.laocat.handler.BcFailureHandler;
import com.laocat.handler.BcPermissionEvaluator;
import com.laocat.handler.BcSuccessHandler;
import com.laocat.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * @Author: Lao Cat
 * @Date: 2020/5/13 14:37
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final BcFailureHandler bcFailureHandler;
    private final BcSuccessHandler bcSuccessHandler;
    private final BcAccessDeniedHandler bcAccessDeniedHandler;

    private final UserService userService;

    public WebSecurityConfig(UserService userService, BcFailureHandler bcFailureHandler, BcSuccessHandler bcSuccessHandler, BcAccessDeniedHandler bcAccessDeniedHandler) {
        this.userService = userService;
        this.bcFailureHandler = bcFailureHandler;
        this.bcSuccessHandler = bcSuccessHandler;
        this.bcAccessDeniedHandler = bcAccessDeniedHandler;
    }

    /**
     * 注入身份管理器
     *
     * @Author: Lao Cat
     * @date: 2020/5/13 15:43
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * 注入自定义权限管理
     *
     * @Author: Lao Cat
     * @date: 2020/5/13 15:45
     */
    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        defaultWebSecurityExpressionHandler.setPermissionEvaluator(new BcPermissionEvaluator());
        return defaultWebSecurityExpressionHandler;
    }

    /**
     * 加密解密 自定义md5
     *
     * @Author: Lao Cat
     * @date: 2020/5/13 15:46
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userService).passwordEncoder(
                new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence charSequence) {
                        return charSequence.toString();
                    }

                    @Override
                    public boolean matches(CharSequence charSequence, String s) {
                        return s.equals(charSequence.toString());
                    }
                }
        );
    }


    /**
     * Security 安全管理器
     *
     * @Author: Lao Cat
     * @date: 2020/5/13 15:54
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // 注册自定义成功 失败处理器
                .failureHandler(bcFailureHandler)
                .successHandler(bcSuccessHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .formLogin()
                .loginPage("/login")
                // 自定义 登录路径
                .loginProcessingUrl("/authentication/form")
                .and()
                // 对请求授权
                .authorizeRequests()
                // 这些页面不需要身份验证
                .antMatchers("/login", "/authentication/require", "/authentication/form").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(bcAccessDeniedHandler)
                .and()
                .csrf().disable();

    }

}

