package com.charly.eazybank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // On the request we specify that any request shall be authenticated
        //http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
        //http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.authorizeHttpRequests(
                (requests) -> requests
                        .requestMatchers(
                                "/api/myAccount",
                                "api/myBalance",
                                "api/myLoans",
                                "api/myCards"
                        ).authenticated()
                        .requestMatchers(
                                "api/notices",
                                "api/contact",
                                "/error" // Secured by default if not specified
                        ).permitAll()
        );

        // WithDefaults is functional inteface which its method
        // return an empty lambda by default
        http.formLogin(AbstractHttpConfigurer::disable);
        // Let u authenticate without a formLogin
        http.httpBasic(withDefaults());
        return http.build();
    }

}
