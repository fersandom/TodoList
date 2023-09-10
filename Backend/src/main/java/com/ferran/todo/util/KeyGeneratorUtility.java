package com.ferran.todo.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import org.springframework.stereotype.Component;

@Component
public class KeyGeneratorUtility {

	public static KeyPair generateRSAKey() {
		KeyPair keyPair;
		
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			keyPair = keyPairGenerator.generateKeyPair();
			
		} catch (Exception e) {
			throw new IllegalStateException();
		}
		
		return keyPair;
	}
}
