package org.springframework.samples.petclinic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
			.requestMatchers("/login", "/oauth2/**", "/webjars/**", "/resources/**")
			.permitAll()
			.anyRequest()
			.authenticated())
			.oauth2Login(oauth2Login -> oauth2Login.loginPage("/login")
				.defaultSuccessUrl("/")
				.failureUrl("/login?error=true")

			)
			.csrf(c -> c.disable())
			.logout(c -> c.logoutUrl("/logout").invalidateHttpSession(true).permitAll());
		return http.build();
	}

}
