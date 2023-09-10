package com.ferran.todo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ferran.todo.util.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class SecurityConfiguration {
	
	private final RSAKeyProperties keyProperties;
	
	public SecurityConfiguration(RSAKeyProperties keyProperties) {
		this.keyProperties = keyProperties;
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(encoder());
		return new ProviderManager(daoAuthenticationProvider);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> {
			auth.anyRequest().permitAll();
		});
		return httpSecurity.build();
	}
	
	@Bean
	public JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(keyProperties.getPublicKey()).build();
	}
	
	@Bean
	public JwtEncoder jwtEncoder() {
		JWK  jwk= new RSAKey.Builder(keyProperties.getPublicKey()).privateKey(keyProperties.getPrivateKey()).build();
		JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwkSource);
	}
}
