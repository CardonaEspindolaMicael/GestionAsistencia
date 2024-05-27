package com.micael.spring.app.security;


import com.micael.spring.app.security.filters.JWTAuthenticationFilter;
import com.micael.spring.app.security.filters.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class SpringSecurityConfig {
 @Autowired
 private AuthenticationConfiguration authenticationConfiguration;

@Bean
AuthenticationManager authenticationManager() throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
}

 @Bean
 PasswordEncoder passwordEncoder (){
     return new BCryptPasswordEncoder();
 }
 @Bean
 DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     //If we want to allow the update request we must to had "/usuarios/{id}" instead /usuarios
     return http.authorizeHttpRequests((authz)->{
       authz.requestMatchers("/usuarios").permitAll()

               .anyRequest().authenticated();
     }).addFilter(new JWTAuthenticationFilter(authenticationManager())).addFilter(new JwtValidationFilter(authenticationManager()))
             .csrf(config-> {
                 try {
                     config.disable().cors(cors->cors.configurationSource(corsConfigurationSource()));
                 } catch (Exception e) {
                     throw new RuntimeException(e);
                 }
             }).sessionManagement(management -> {
         management.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
     }).build();
 }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(
                new CorsFilter(corsConfigurationSource()));
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsBean;
    }

}
