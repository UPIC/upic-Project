package com.upic.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableWebSecurity
//@EnableOAuth2Sso
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

//	@Autowired
//	private DataSource dataSource;

//	@Bean
//	public PersistentTokenRepository persistentTokenRepository() {
//		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//		// tokenRepository.setCreateTableOnStartup(true);
//		tokenRepository.setDataSource(dataSource);
//		return tokenRepository;
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		SpringSocialConfigurer configurer=new SpringSocialConfigurer();
		configurer.signupUrl("/regist.html");
		http.
		httpBasic().
		and().
		formLogin().loginPage("/login.html").usernameParameter("user").passwordParameter("pass")
				.successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
				.loginProcessingUrl("/auth").and()
//				.rememberMe().tokenRepository(persistentTokenRepository()).and()
//				.sessionManagement()
//				.invalidSessionUrl("/session.html")
//				.maximumSessions(1)
//				.maxSessionsPreventsLogin(true).and()
//				.and()
				.csrf().
				disable()
//				csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
				.authorizeRequests().antMatchers("/connect/**","/signin","/stu", "/login.html", "/login","/auth","/session.html","/index.html","/auth/**","/regist.html", "/getRegistUserInfo","/registUser").permitAll()
				.anyRequest()
				.access("@checkAllSecurity.check(authentication,request)")
//				.authenticated()
				.and()
				.apply(configurer)
				;
	}

}