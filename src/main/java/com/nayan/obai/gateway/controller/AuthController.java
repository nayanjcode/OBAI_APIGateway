//package com.nayan.obai.gateway.controller;
//
//import com.nayan.obai.gateway.entity.AuthResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController
//{
//
//	@Autowired
//	private ReactiveOAuth2AuthorizedClientService clientService;
//
//	@GetMapping("/login")
//	ResponseEntity<AuthResponse> login(@AuthenticationPrincipal Mono<OidcUser> oidcUser){
//		return new ResponseEntity<>(new AuthResponse(), HttpStatus.ACCEPTED);
//	}
//}
