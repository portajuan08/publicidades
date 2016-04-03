package monkey.woodstock.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;


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
    		/*
        httpSecurity.authorizeRequests().anyRequest().authenticated();

        httpSecurity.formLogin().failureUrl("/login?error")
                .defaultSuccessUrl("/")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .permitAll();
*/
    	httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
        .authorizeRequests().antMatchers("/console/**").permitAll();

		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }

}
