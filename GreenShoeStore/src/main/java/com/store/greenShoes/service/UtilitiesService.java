package com.store.greenShoes.service;

import java.security.MessageDigest;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.store.greenShoes.Constants.Constants;
import com.store.greenShoes.DTO.OrderDTO;
import com.store.greenShoes.repository.*;

public class UtilitiesService {
	@Autowired
	private static ProductRepository productRepo;
	static SecureRandom random = new SecureRandom();

	public static String GetRandomPassword() {
		String tempPswd = generateRandomPassword();
		return tempPswd;
	}

	public static String generateRandomPassword() {
		String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
		String CHAR_UPPER = CHAR_LOWER.toUpperCase();
		String NUMBER = "0123456789";
		String OTHER_CHAR = "!@#$%&*()_+[]?";

		String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;

		StringBuilder sb = new StringBuilder(8);
		for (int i = 0; i < 8; i++) {
			int randomIndex = random.nextInt(PASSWORD_ALLOW_BASE.length());
			sb.append(PASSWORD_ALLOW_BASE.charAt(randomIndex));
		}

		// Ensure at least one lowercase, one uppercase, one number, and one special
		// character
		sb.append(CHAR_LOWER.charAt(random.nextInt(CHAR_LOWER.length())));
		sb.append(CHAR_UPPER.charAt(random.nextInt(CHAR_UPPER.length())));
		sb.append(NUMBER.charAt(random.nextInt(NUMBER.length())));
		sb.append(OTHER_CHAR.charAt(random.nextInt(OTHER_CHAR.length())));

		// Shuffle the characters
		String password = sb.toString();
		return shuffleString(password);
	}

	private static String shuffleString(String string) {
		char[] charArray = string.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			int randomIndex = random.nextInt(charArray.length);
			char temp = charArray[i];
			charArray[i] = charArray[randomIndex];
			charArray[randomIndex] = temp;
		}
		return new String(charArray);
	}

	public static String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

}
