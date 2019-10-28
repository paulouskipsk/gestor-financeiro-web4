package br.edu.utfpr.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public final class FlashMessage {
	private static Map<String, String> message = new HashMap<String, String>();
	
	public static Map getMessages() {
		Map<String, String> aux = message;
		message = new HashMap<String, String>();
		return aux;
	}
	
	public static void setMessage(String key, String value) {
		message.put(key, value);
	}
	
	public static boolean isEmpty() {	
		return message.isEmpty();
	}
}