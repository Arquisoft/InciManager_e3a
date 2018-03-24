package uo.asw.inciManager.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AgentService {
	
	private String idConnectedAgent;

	/**
	 * Metodo que sirve de comunicacion con agents , se le envia el usuario, login y pass
	 * y devuelve el id del usuario.
	 * @param login
	 * @param password
	 * @param kind
	 * @return
	 */
	public String communicationAgents(String login, String password, String kind) {
		String urlAgents = "http://localhost:8091/user";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("login", login);
		map.put("password", password);
		map.put("kind", kind);
		HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(urlAgents, request, String.class);
		JSONObject json;
		if (response.getStatusCode() == HttpStatus.OK) {
			try {
				json = new JSONObject(response.getBody());
				System.out.println("------------ OK -------------");
				return json.getString("id");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else{
			System.out.print("------------ ERROR -------------");
		}
		return null;
	}
	
	public void setIdConnected(String idAgent) {
		idConnectedAgent = idAgent;
	}
	
	public String getIdConnected() {
		return idConnectedAgent;
	}
}
