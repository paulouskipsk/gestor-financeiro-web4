package br.edu.utfpr.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Constants {
	public static final String PERSISTENCE_UNIT_NAME = "finances_manager";
	public static final String LAST_SERVER_INITIALIZATION = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss").format(new Date());	
	public static final String RECAPTCHA_KEY_SECRET = "6LfGzsAUAAAAANEnW7mQugDQYDPh4d4GIrWaIdIb";
	public static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
	
	public static class RoleAccess{
		public static final String ADM = "ADM";
		public static final String USR = "USR";
		
		public static String getDescriptionFromAbrev(String abrev) {
			switch (abrev){
				case "ADM": return "Administrador";
				case "USR": return "Usuario";
				default: return null;
			}
		}	
		
		public static Map<String, String> getRolesAccess() {
			Map<String, String> roles = new HashMap<String, String>();
			roles.put("ADM", "Administrador");
			roles.put("USR", "Usuario");
			
			return roles;
		}	
	}
	
	public static class Messages{
		public static String $E0001 = "Erro ao salvar Usuario.";
		
		public static String $I0001 = "";

		public static String $S0001 = "Usuario Salvo com sucesso.";
		
	}
	
	public static class Routes{
		public static final String ROOT_PATH_VIEWS = "WEB-INF/views/";
		public static final String HOME = "home";
		public static final String LOGIN = "login";
		public static final String LOGOUT = "logout";
	}
}
