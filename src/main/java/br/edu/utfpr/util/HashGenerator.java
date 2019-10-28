package br.edu.utfpr.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {

	public static String getSha256(String text) {
		MessageDigest sha256 = null;
		try {
			sha256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		sha256.update(StandardCharsets.UTF_8.encode(text));
		return String.format("%032x", new BigInteger(1, sha256.digest()));
	}

}
