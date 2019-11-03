package br.edu.utfpr.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.net.ssl.HttpsURLConnection;

import org.primefaces.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.edu.utfpr.util.Constants;
import br.edu.utfpr.util.FlashMessage;

public class Recaptcha {
	
	public static boolean verify(String gRecaptchaResponse) throws IOException {
		if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
			return false;
		}
				
		try{
			URL obj = new URL(Constants.RECAPTCHA_VERIFY_URL);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			
			// add reuqest header
			con.setRequestMethod("POST");
			String postParams = "secret=" + Constants.RECAPTCHA_KEY_SECRET + "&response="+ gRecaptchaResponse;
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();
			String[] teste = (response.toString()).split("[{}:,]");

			return  teste[2].trim().equals("true");
		}catch(Exception e){
			e.printStackTrace();
			FlashMessage.addMessage("danger", "Exception:" +e);
			return false;
		}
	}
}