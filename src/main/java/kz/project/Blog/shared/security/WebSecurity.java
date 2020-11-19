package kz.project.Blog.shared.security;

import kz.project.Blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static kz.project.Blog.shared.security.SecurityConstants.*;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, REGISTER).permitAll()
                .antMatchers(LOGIN_URL).permitAll()
                .antMatchers(SWAGGER_URL).permitAll()
//                .antMatchers(HttpMethod.GET, "**").permitAll()
                .anyRequest().authenticated()
                .and()
                .antMatcher("/**").cors().and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), userService))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), userService));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
}
