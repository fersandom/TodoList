package com.ferran.todo.util;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class RSAKeyProperties {

	private RSAPublicKey publicKey;
	private RSAPrivateKey privateKey;
	
	public RSAKeyProperties() {
		KeyPair pair = KeyGeneratorUtility.generateRSAKey();
		this.publicKey = (RSAPublicKey) pair.getPublic();
		this.privateKey = (RSAPrivateKey) pair.getPrivate();
	}
	
	
}
