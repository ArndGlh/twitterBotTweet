package com.example.springsocialdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// Proxy
		System.setProperty("http.proxyPort", ""); // TODO Proxy port
        System.setProperty("http.proxyHost", ""); // TODO Proxy host

        // Authentification
//		final String proxyUser = System.getProperty("http.proxyUser");
//		final String proxyPassword = System.getProperty("http.proxyPassword");
//
//		if (proxyUser != null && proxyPassword != null) {
//			Authenticator.setDefault(
//					new Authenticator() {
//						public PasswordAuthentication getPasswordAuthentication() {
//							return new PasswordAuthentication(
//									proxyUser, proxyPassword.toCharArray()
//							);
//						}
//					}
//			);
//		}
		SpringApplication.run(Application.class, args);
	}
}