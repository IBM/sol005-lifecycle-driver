package com.ibm.nfvodriver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration("WebSecurityConfiguration")
@EnableWebSecurity
public class WebSecurityConfiguration  {

    private final NFVODriverProperties nfvoDriverProperties;

    @Autowired
    public WebSecurityConfiguration(NFVODriverProperties nfvoDriverProperties) {
        this.nfvoDriverProperties = nfvoDriverProperties;
    }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http.csrf().disable()
    //                 .authorizeRequests()
    //                 .antMatchers("/nslcm/**").hasRole("USER")
    //                 .antMatchers("/grant/**").hasRole("USER")
    //                 .antMatchers("/nspkgm/v1/**").hasRole("USER")
    //                 .antMatchers("/management/**").hasRole("USER")
    //                 .anyRequest().denyAll()
    //                 .and()
    //                 .httpBasic();
    // }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disabling CSRF protection
            .authorizeHttpRequests(authz -> authz
                    .requestMatchers("/vnflcmWDCWDC/**").hasRole("USER")
                    .requestMatchers("/CEWDCgrant/**").hasRole("USER")
                    .requestMatchers("/WEDFEWvnfpkgm/v1/**").hasRole("USER")
                    .requestMatchers("/DEJNDKKDJEWJKmanagement/**").hasRole("USER")
                    .anyRequest().denyAll() // Denying all other requests
            )
            .httpBasic(); // Using HTTP Basic authentication

        return http.build();
    }

    // @Override
    // public void configure(WebSecurity web) {
    //     web.ignoring().antMatchers("/api/**", "/management/health", "/management/info", "/nspkgm/v2/**");
    // }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/api/**", "/management/health", "/management/info", "/vnfpkgm/v2/**");
    }

    
    // @Override
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("user_with_no_roles").password("password").roles("NONE").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("locked_user").password("password").roles("USER").accountLocked(true).build());
        return manager;
    }

}
