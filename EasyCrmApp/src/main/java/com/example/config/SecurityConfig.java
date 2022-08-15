package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.util.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration //実行時にこれらの Bean の Bean 定義とサービスリクエストを生成
@EnableWebSecurity //  Spring Securityを使うための設定
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
//		セキュリティ設定を無視する
		web.ignoring().antMatchers("/css/**","/img/**","/webjars/**","/h2-console/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests()
		.antMatchers("/login","/error","/register").permitAll() //すべての人がアクセスできる
		.antMatchers("/admin/**").hasRole(Role.ADMIN.name())
		.anyRequest().authenticated()
		.and().formLogin() //ログイン認証
		.loginPage("/login") //ログイン時のURL
		.defaultSuccessUrl("/") //認証後に遷移する画面
		.and().logout() //ログアウトの設定
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //ログアウト時のURLを指定
		.and().rememberMe(); //Remember-Me の 認証 を 許可 し ます
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)  throws Exception{
		
//		DBからユーザを参照
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	
	}
}
