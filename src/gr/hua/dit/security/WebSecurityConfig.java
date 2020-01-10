package gr.hua.dit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
//  Old Working
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.loginProcessingUrl("/loginAction").permitAll().and().logout().logoutSuccessUrl("/login").permitAll()
				.and().csrf().disable();
	}
//
//	 	@Override
//	    protected void configure(HttpSecurity http) throws Exception {
//	        http.authorizeRequests()
//	        .antMatchers("/login")
//	            .permitAll()
//	        .antMatchers("/**")
//	            .hasAnyRole("ROLE_ADMIN", "ROLE_EMP", "ROLE_CHIEF")
//	        .and()
//	            .formLogin()
//	            .loginPage("/login")
//	            .defaultSuccessUrl("/home")
//	            .failureUrl("/login?error=true")
//	            .permitAll()
//	        .and()
//	            .logout()
//	            .logoutSuccessUrl("/login?logout=true")
//	            .invalidateHttpSession(true)
//	            .permitAll()
//	        .and()
//	            .csrf()
//	            .disable();
//	    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");

		web.ignoring().antMatchers("/api/**");

	}
}