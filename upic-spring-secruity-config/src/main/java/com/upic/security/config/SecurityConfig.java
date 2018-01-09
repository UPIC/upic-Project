package com.upic.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableWebSecurity
// @EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private MyAuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private MyAuthenticationFailureHandler authenticationFailureHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// @Autowired
	// private DataSource dataSource;
	// @Bean
	// public PersistentTokenRepository persistentTokenRepository() {
	// JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
	// tokenRepository.setCreateTableOnStartup(true);
	// tokenRepository.setDataSource(dataSource);
	// return tokenRepository;
	// }

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		SpringSocialConfigurer configurer = new SpringSocialConfigurer();
		// configurer.signupUrl("/casgo");
		configurer.signupUrl("/cas");
		http.httpBasic().and().headers().frameOptions().disable().and().formLogin().loginPage("/upiclogin")
				.usernameParameter("user").passwordParameter("pass").successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler).loginProcessingUrl("/auth").and()
				// .rememberMe().tokenRepository(persistentTokenRepository()).and()
				// .sessionManagement()
				// .invalidSessionUrl("/session.html")
				// .maximumSessions(1)
				// .maxSessionsPreventsLogin(true).and()
				// .and()
				.csrf().disable()
				// csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
				.authorizeRequests()
				.antMatchers("/connect/**", "/metroWeb/html/login.html", "/upiclogin", "/auth", "/auth/**", "/cas",
						"/casgo", "/login","/js/**", "/css/**","/img/**")
				.permitAll().anyRequest().access("@checkAllSecurity.check(authentication,request)")
				// .authenticated()
				.and().apply(configurer);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 解决静态资源被拦截的问题
		web.ignoring().antMatchers("/js/**", "/css/**","/img/**");
	}

}
