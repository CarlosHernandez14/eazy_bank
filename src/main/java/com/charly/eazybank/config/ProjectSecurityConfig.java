package com.charly.eazybank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
// Optional annotation used in case you're app runs on normal spring app (not spring boot)
//@EnableWebSecurity
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // On the request we specify that any request shall be authenticated
        //http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
        //http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                (requests) -> requests
                        .requestMatchers(
                                "/api/myAccount",
                                "/api/myBalance",
                                "/api/myLoans",
                                "/api/myCards"
                        ).authenticated()
                        .requestMatchers(
                                "api/notices",
                                // All the requests to the /api/users are permitted
                                "/api/users/**",
                                "api/contact",
                                "/error" // Secured by default if not specified
                        ).permitAll()
        );

        // WithDefaults is functional inteface which its method
        // return an empty lambda by default
        //http.formLogin(AbstractHttpConfigurer::disable);
        http.formLogin(withDefaults());
        // Let u authenticate without a formLogin
        http.httpBasic(withDefaults());
        return http.build();
    }

    // In charge to manage the user accounts to help the AuthenticationManager
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        // Inyect DataSource bean to the JdbcUserDetailsManager (Connection to the DB)
//        return new JdbcUserDetailsManager(dataSource);
//    }

    // Manage the password encoding hashing
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Permit us to use different types of encoding (default is bcrypt)
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }



}
