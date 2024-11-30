package SwProject.config.spring;

import SwProject.SpringSecurity.jwt.JwtAuthEntryPoint;
import SwProject.SpringSecurity.jwt.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests->
                        requests
                                .requestMatchers("/static/**").permitAll()
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/verify/**").permitAll()
                                .requestMatchers (
                                        "/swagger-resources/**",
                                        "/swagger-ui/**",
                                        "/v3/api-docs",
                                        "/api-docs/**",
                                        "/v3/api-docs/swagger-config",
                                        "/favicon.ico",
                                        "/public/**"
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(config->
                        config.authenticationEntryPoint(this.jwtAuthEntryPoint) //인증되지 않은 사용자 접근시 에러 발생
                )
                .sessionManagement(it->
                        it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 사용 안함
                )
                .addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

//SWAGGER 주소 : http://localhost:8080/swagger-ui/index.html