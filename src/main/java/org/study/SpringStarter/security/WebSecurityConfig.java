package org.study.SpringStarter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.study.SpringStarter.util.constants.Privillages;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {
        private static final String[] WHITELIST = {

                        "/login",
                        "/register",
                        "/db-console/**",
                        "/css/**",
                        "/fonts/**",
                        "/images/**",
                        "/js/**"

        };

        @Bean
        public static PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.authorizeRequests(requests -> requests
                                .requestMatchers(WHITELIST)
                                .permitAll()
                                .requestMatchers("/profile/**").authenticated()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/editor/**").hasAnyRole("ADMIN", "EDITOR")
                                .requestMatchers("/admin/**")
                                .hasAuthority(Privillages.ACCESS_ADMIN_PANEL.getPrivillage()))
                                .formLogin(login -> login
                                                .loginPage("/login")
                                                .loginProcessingUrl("/login")
                                                .usernameParameter("email")
                                                .passwordParameter("password")
                                                .defaultSuccessUrl("/", true)
                                                .failureUrl("/login?error")
                                                .permitAll())
                                .rememberMe(rememberMe -> rememberMe
                                                .key("uniqueAndSecret")
                                                .rememberMeParameter("remember-me")
                                                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30)))
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login"))
                                .httpBasic(withDefaults());

                // TODO:remove these after uograding the Db from H2 infile
                http.csrf(csrf -> csrf.disable());
                http.headers(headers -> headers.frameOptions().disable());

                return http.build();
        }
}