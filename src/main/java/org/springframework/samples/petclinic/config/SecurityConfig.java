package org.springframework.samples.petclinic.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.system.CustomOAuth2UserService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomOAuth2UserService customOAuth2UserService)
			throws Exception {
		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
			.permitAll()
			.requestMatchers("/login", "/oauth2/**", "/resources/**", "/actuator/health", "/h2-console/**", "/*.css",
					"/*.js", "/*.map", "/error", "/oups", "/target/*.map")
			.permitAll()
			.anyRequest()
			.authenticated())
			.oauth2Login(oauth2Login -> oauth2Login.loginPage("/login")
				.defaultSuccessUrl("/")
				.failureUrl("/login?error=true")
				.userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService)))
			.csrf(c -> c.disable())
			// Allow frames for H2 Console
			.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()) // Allow
																								// frames
																								// for
																								// H2
																								// Console
			)
			.logout(c -> c.logoutUrl("/logout").invalidateHttpSession(true).permitAll());
		return http.build();
	}

}
