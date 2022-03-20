package com.myapp.uranuscapstone.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.myapp.uranuscapstone.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	/*
	 * @Autowired private DataSource dataSource;
	 */

	@Autowired
	CustomUserDetailsService customUserDetailService;

	/*
	 * @Bean public UserDetailsService userDetailsService() { return new
	 * CustomUserDetailsService(); }
	 */

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * @Bean public DaoAuthenticationProvider authenticationProvider() {
	 * DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	 * authProvider.setUserDetailsService(userDetailsService());
	 * authProvider.setPasswordEncoder(passwordEncoder());
	 * 
	 * return authProvider; }
	 */

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.authenticationProvider(authenticationProvider()); }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http.authorizeRequests() .antMatchers("/index").authenticated()
		 * .anyRequest().permitAll() .and() .formLogin() .usernameParameter("email")
		 * .defaultSuccessUrl("/index") .permitAll() .and()
		 * .logout().logoutSuccessUrl("/").permitAll();
		 */
		http.authorizeRequests()
				.antMatchers("/", "/register", "/contact", "/About", "/index/product_list", "/css/**", "/image/**",
						"/product-photos/**", "/userImage/**")
				.permitAll().antMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().failureUrl("/login?error=true").defaultSuccessUrl("/", true)
				.usernameParameter("email").passwordParameter("password").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/sign-out")).logoutSuccessUrl("/login")
				.invalidateHttpSession(true).deleteCookies("JSESSIONID").and().exceptionHandling().and().csrf()
				.disable();
		http.headers().frameOptions().disable();

	}

	// galing sa gawa ni raymond

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService);
		auth.inMemoryAuthentication().withUser("user").password("password").roles("ADMIN");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/images/**", "/product-photos/**", "css/**",
				"/js/**", "/img/**");
	}

}
