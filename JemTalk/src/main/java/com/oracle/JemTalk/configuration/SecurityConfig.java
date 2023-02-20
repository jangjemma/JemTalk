package com.oracle.JemTalk.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//IoC 빈(bean)을 등록
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//filter chain을 관리를 시작하는 annotation이다
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) //특정 주소 접근시 권한 및 인증
public class SecurityConfig {
	
	//인스턴스 형성 @Autowired 로 사용
	//spring security에서 제공하는 암호화 기법
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// csrf는 해킹기법
		http.csrf().disable();
		//인증할 시 어떤것이든 허용해주겠다
		//Authentication 인증 (내가 사용자인가 아닌가)
		//authorization 인가 (인증은 받았지만 권한을 확인)
		http.authorizeRequests().antMatchers("/user/**").authenticated()
		.antMatchers("/admin/**").access("hasRole('ROLE_MANAGER')")
		.and().formLogin().loginPage("/loginForm").loginProcessingUrl("/login").failureUrl("/loginFail").defaultSuccessUrl("/loginSuccess");
		// authenticated -> user/**은 인증필요-->인증만되면 들어갈 수 있음
		//http.authorizeRequests().anyRequest().permitAll();
		http.exceptionHandling().accessDeniedPage("/denied");
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/logoutForm");

		return http.build();
	}
	
}
