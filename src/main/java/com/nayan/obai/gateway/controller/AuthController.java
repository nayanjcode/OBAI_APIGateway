package com.nayan.obai.gateway.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthController
{
	@GetMapping("/")
	public Mono<String> home(@AuthenticationPrincipal Mono<OidcUser> userMono)
	{
		return userMono
				.map(user -> "Hello, " + user.getFullName() + "!")
				.defaultIfEmpty("Hello, Guest! <a href='/oauth2/authorization/auth0'>Login with Auth0</a>");
	}

	private final ReactiveOAuth2AuthorizedClientService authorizedClientService;

	public AuthController(ReactiveOAuth2AuthorizedClientService service) {
		this.authorizedClientService = service;
	}

//	 reactive token getter
	@GetMapping("/token")
	public Mono<String> getToken(@AuthenticationPrincipal Mono<OAuth2AuthenticationToken> authenticationMono) {
		return authenticationMono.flatMap(auth ->
				authorizedClientService
						.loadAuthorizedClient(auth.getAuthorizedClientRegistrationId(), auth.getName())
						.map(client -> client.getAccessToken().getTokenValue())
		);
	}

	// for non reactive-applications
//	@GetMapping("/")
//	public String index(@AuthenticationPrincipal OidcUser oidcUser) {
//		return "Hello, " + oidcUser.getFullName() + "!";
//	}

	// non-reactive token getter
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
