package com.saapi.saapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.saapi.saapi.service.ClientUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClientUserDetailsService userDetailsService; 

	// @Bean
	// PasswordEncoder passwordEncoder(){
	//    return new BCryptPasswordEncoder();
	// }

    //Basic Authorization for the APIs
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("shawn90").password("2950Cherry*30").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("userwolf").password("2022").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http.csrf().disable();
		http.authorizeRequests()
		 	.antMatchers(HttpMethod.GET, "/api/**").permitAll()
		 	.antMatchers("/api/auth/**").permitAll()
		 	.antMatchers("/api/users").hasAuthority("ADMIN")
		 	.anyRequest().fullyAuthenticated().and().httpBasic()
		 	.and().exceptionHandling().accessDeniedPage("/403");		
	}
    
    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors();
//        http.csrf().disable()
//            .authorizeRequests()
//            .antMatchers(HttpMethod.GET, "/api/**").permitAll()
//            .antMatchers("/api/auth/**").permitAll()
//            // .antMatchers("/api/users").hasAuthority("ROLE_ADMIN")          
//            .anyRequest().authenticated().and().httpBasic()
//            .and().exceptionHandling().accessDeniedPage("/403");
//    }
    
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());    	
//    }     
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }  
    
    //no password encrypt, just take plain text password
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
    	return ( NoOpPasswordEncoder)NoOpPasswordEncoder.getInstance();    	
    } 

}
