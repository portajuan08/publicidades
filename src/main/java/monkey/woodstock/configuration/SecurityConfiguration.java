package monkey.woodstock.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration //annotation lets Spring know that this file contains configuration information
/*
 *  setup the automatically-configured portions of our security scheme, provided by Spring-Boot.
 *  EnableWebMvcSecurity basically pulls in the default SpringSecurity/SpringMVC integration. It’s an extension of the
 *  WebMvcConfigurerAdapter, and adds methods for handling and generating CSRF tokens and resolving the logged in user,
 *  and configures default AuthenticationManagers and Pre/Post object authorization implementations.
 */
@EnableWebMvcSecurity
/*
 * sets up processors for authorization advice that can be added around methods and classes. This authorization advice
 * lets a developer write Spring EL that inspects input parameters and return types.
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)

/*
 * Our SecurityConfiguration class also extends WebSecurityConfigurerAdapter. In Spring/Spring Boot, Configurer Adapters
 * are classes that construct default bean configurations and contain empty methods which are meant to be overridden.
 * Overriding these methods allow a developer to customize the Web Security Configuration during startup. Typically,
 * the default configurations are constructed, and immediately following, the empty methods are called. If you’ve
 * overridden an empty method, you’re able to inject custom behavior into the default configuration during the startup
 * of the container.
 */
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

    	httpSecurity.csrf().disable();
    	httpSecurity.headers().frameOptions().disable();
    	httpSecurity
         .authorizeRequests()
             .antMatchers("/webjars/**","/images/**", "?lang=en", "?lang=es").permitAll()
             .anyRequest().authenticated();
    	httpSecurity
    	.formLogin().failureUrl("/login?error")
        .loginPage("/login").defaultSuccessUrl("/")
        .usernameParameter("username").passwordParameter("password")
        .successHandler(successHandler())
        .permitAll()
        .and()
         .logout()
             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
             .logoutSuccessUrl("/");

    }
    
    @Autowired
    DataSource dataSource;
    
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
      auth.jdbcAuthentication().dataSource(dataSource)
     .usersByUsernameQuery(
      "select username,password, enabled from user where username=?")
     .authoritiesByUsernameQuery(
      "select username, rol from user_rol  where username=?");
    } 

/*    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("password").roles("USER","ADMIN");
    }
  */  
    @Bean
    public AuthenticationSuccessHandler successHandler() {
    	return new MyCustomLoginSuccessHandler("/");
    }
}
