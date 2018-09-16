package com.nixsolutions.xxe_demo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@ComponentScan(basePackages = "com.nixsolutions.xxe_demo.config.security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
  @Autowired
  private AuthenticationProvider authenticationProvider;


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception
  {
    auth.authenticationProvider(authenticationProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http.csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.PUT, "/notes").authenticated()
        .antMatchers(HttpMethod.GET, "/notes/**").authenticated()
        .antMatchers("/register").permitAll()
        .and()
        .httpBasic();
  }
}
