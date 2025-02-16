package com.charly.eazybank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

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
        //http.formLogin(AbstractHttpConfigurer::disable);
        http.formLogin(withDefaults());
        // Let u authenticate without a formLogin
        http.httpBasic(withDefaults());
        return http.build();
    }

    // In charge to manage the user accounts to help the AuthenticationManager
    @Bean
    public UserDetailsService userDetailsService() {
        // The password is encoded with {noop} to indicate that the password is not encoded
//        UserDetails user = User.withUsername("user").password("{noop}12345").authorities("read").build();
        UserDetails user = User.withUsername("user")
                .password("{noop}admin@1234.com")
                .authorities("read")
                .build();

        UserDetails admin = User.withUsername("admin")
                // The password is encoded with {bcrypt} to indicate that the password is encoded with bcrypt
                .password("{bcrypt}$2a$12$y5YBopT5tAw2mmH6o1/PKuY9OTVF/Dg/MJytJW4wU7Z5Bqn6LNoje")
                .authorities("admin")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
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
