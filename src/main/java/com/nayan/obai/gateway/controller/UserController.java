package com.nayan.obai.gateway.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserController
{
//	@GetMapping("/")
//	public String index(@AuthenticationPrincipal OidcUser oidcUser) {
//		return "Hello, " + oidcUser.getFullName() + "!";
//	}

	@GetMapping("/")
	public Mono<String> home(@AuthenticationPrincipal Mono<OidcUser> userMono)
	{
		return userMono
				.map(user -> "Hello, " + user.getFullName() + "!")
				.defaultIfEmpty("Hello, Guest! <a href='/oauth2/authorization/auth0'>Login with Auth0</a>");
	}

//	private final OAuth2AuthorizedClientService authorizedClientService;
//
//	public UserController(OAuth2AuthorizedClientService service) {
//		this.authorizedClientService = service;
//	}
//
//	@GetMapping("/token")
//	public String getToken(@AuthenticationPrincipal OAuth2AuthenticationToken authentication) {
//		OAuth2AuthorizedClient client = authorizedClientService
//				.loadAuthorizedClient(
//						authentication.getAuthorizedClientRegistrationId(),
//						authentication.getName()
//				);
////		client.getClientRegistration();
//		return client.getAccessToken().getTokenValue();
//	}
}
