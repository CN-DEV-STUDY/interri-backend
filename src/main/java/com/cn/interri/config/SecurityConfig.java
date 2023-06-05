package com.cn.interri.config;

import com.cn.interri.config.jwt.JwtAuthenticationFilter;
import com.cn.interri.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * https://gksdudrb922.tistory.com/217#JwtTokenProvider
 * 과거에는 Security 설정을 WebSecurityConfigurerAdapter 클래스를 상속받아서 구현했지만,
 * Spring Boot 버전이 올라가면서 해당 방식은 Deprecated 되었다. 따라서 이제는 빈 등록을 통해 Security를 설정한다.
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Qualifier("customAuthenticationEntryPoint")
    private final AuthenticationEntryPoint authEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // JWT를 사용하기 위해서는 기본적으로 password encoder가 필요한데, 여기서는 Bycrypt encoder를 사용했다.
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable() // rest api이므로 basic auth 및 csrf 보안을 사용하지 않는다는 설정이다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT를 사용하기 때문에 세션을 사용하지 않는다는 설정이다.
                .and()
                .authorizeHttpRequests(requests -> requests
                        // 인증이 필요한 페이지
                        .requestMatchers("/design/**").authenticated()
                        .anyRequest().permitAll())
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행하겠다는 설정이다.
        return http.build();
    }


}
