package com.nayan.obai.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig
{
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity http)
	{
		return http
				.authorizeExchange(auth -> auth
						// login and oauth2 endpoints will be used by spring so need to permit them
						.pathMatchers("/login**", "/oauth2/**").permitAll()
						.anyExchange().authenticated()
				)
				.oauth2Login(Customizer.withDefaults())
				// this will required as we want to authenticate using client/browser
				.oauth2Client(Customizer.withDefaults())
				.logout(logout -> logout
						.logoutSuccessHandler(new RedirectServerLogoutSuccessHandler())
				)
				.build();
	}
}
