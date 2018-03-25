package uo.asw.inciManager.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatBotService {

	@Autowired
	private IncidenciasService incidenciasService;
	
	private Map<String, Object> datosRecogidos = new HashMap<String, Object>();
	
	boolean isCreating = false;
	
	private String mensajes = "+ Hola, agente \n+ ¿En qué le puedo ayudar?";

	public Map<String, Object> getDatosRecogidos() {
		return datosRecogidos;
	}

	public void setDatosRecogidos(Map<String, Object> datosRecogidos) {
		this.datosRecogidos = datosRecogidos;
	}
	
	public boolean comprobarCrear(String pregunta) {
		
		if(pregunta.contains("crear")) {
			isCreating=true;
			return true;
		}
		
		return false;
	}

	public String getMensajes() {
		return mensajes;
	}

	public void setMensajes(String mensajes) {
		this.mensajes = mensajes;
	}

	public boolean isCreating() {
		return isCreating;
	}

	public void setCreating(boolean isCreating) {
		this.isCreating = isCreating;
	}
	
	
}
