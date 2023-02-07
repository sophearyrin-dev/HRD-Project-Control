package com.kshrd.hrdprojectcontrolapi.configurations;

import com.kshrd.hrdprojectcontrolapi.configurations.JwtConfigurations.JwtAuthenticationEntryPoint;
import com.kshrd.hrdprojectcontrolapi.configurations.JwtConfigurations.JwtRequestFilter;
import com.kshrd.hrdprojectcontrolapi.services.users.UserSeviceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserSeviceImp userSeviceImp;
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private BCryptPasswordEncoder encoder;
    JwtRequestFilter jwtRequestFilter;
    @Autowired
    public void setJwtAuthenticationEntryPoint(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }
    @Autowired
    public void setJwtRequestFilter(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }
   @Autowired
   public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }
//    private UserDetailsService userDetailsService;
    @Autowired
    public SecurityConfig(UserSeviceImp userSeviceImp) {
        this.userSeviceImp = userSeviceImp;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSeviceImp).passwordEncoder(encoder);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .deny()
                .and()
                .authorizeRequests()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/v2/api-docs",           // swagger
                        "/webjars/**",            // swagger-ui webjars
                        "/swagger-resources/**",  // swagger-ui resources
                        "/configuration/**",      // swagger configuration
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                .antMatchers("/api/v1/users/authenticate").permitAll()

                .antMatchers(HttpMethod.PUT,"/api/v1/subTask-resolve-issue/*").hasAnyRole("SUPER_MENTOR","MENTOR","GROUP_LEADER","MEMBER")

                .antMatchers(HttpMethod.PUT,"/api/v1/tasks").hasAnyRole("SUPER_MENTOR","MENTOR","GROUP_LEADER")

                .antMatchers(HttpMethod.PUT,"/api/v1/subtasks/*").hasAnyRole("SUPER_MENTOR","GROUP_LEADER","MENTOR","MEMBER")

                .antMatchers(HttpMethod.PUT,"/api/v1/subTask/*").hasAnyRole("SUPER_MENTOR","GROUP_LEADER","MENTOR")

                .antMatchers(HttpMethod.POST,"/api/v1/subtasks-all-insert").hasAnyRole("SUPER_MENTOR","MENTOR","GROUP_LEADER")

                .antMatchers(HttpMethod.DELETE,"/api/v1/subtasks/*").hasAnyRole("SUPER_MENTOR","MENTOR","GROUP_LEADER")

                .antMatchers(HttpMethod.POST,"/api/v1/daily-reports").hasAnyRole("SUPER_MENTOR","GROUP_LEADER","MENTOR","MEMBER")



                .antMatchers(HttpMethod.POST,"/**").hasRole("SUPER_MENTOR")
                .antMatchers(HttpMethod.PUT,"/**").hasRole("SUPER_MENTOR")
                .antMatchers(HttpMethod.DELETE,"/**").hasRole("SUPER_MENTOR")
                .antMatchers(HttpMethod.GET,"/**").hasAnyRole("SUPER_MENTOR", "MENTOR", "GROUP_LEADER", "MEMBER")
//                .antMatchers("/api/v1/generation").hasRole("MEMBER")
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}

