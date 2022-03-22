package com.portifolio.web;


 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
 import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
/** @author Rolfi Luz - Senai * */

 @Configuration
 public class WebConfig extends WebSecurityConfigurerAdapter {
	 
	// Método que configura usuários e escopo de atuação no sistema
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder builder)throws Exception{
			
			builder
			.inMemoryAuthentication()
			.withUser("cesar").password("{noop}cesar").roles("USER")
			.and().withUser("root").password("{noop}root").roles("ADMIN");
			
		}
		
		// Método que configura quais seções do site podem ser acessadas com e sem login
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			
			http
			.authorizeRequests().antMatchers("/").permitAll()
			
			.antMatchers("/_css/**").permitAll()
			.antMatchers("/_imagens/**").permitAll()
			.antMatchers("/_javascript/**").permitAll()
			.antMatchers("/bootstrap/**").permitAll()
			
			.antMatchers("/servicos").permitAll()
			.antMatchers("/sobre").permitAll()
			.antMatchers("/cadastrar").permitAll().anyRequest()
			.authenticated().and().formLogin().permitAll()
			.and().logout().permitAll()
			.and().csrf().disable();
		}
		
	

 }