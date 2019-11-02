package br.edu.utfpr.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public final class FlashMessage {
	private static Map<String, Object> message = new HashMap<String, Object>();
	
	public static Map getMessages() {
		Map<String, Object> aux = message;
		message = new HashMap<String, Object>();
		return aux;
	}
	
	public static void addMessage(String key, Object value) {
		message.put(key, value);
	}

	public static boolean isEmpty() {	
		return message.isEmpty();
	}
	
	public static void clean() {
		message = new HashMap<String, Object>();	
	}
}
