package uo.asw.chatbot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uo.asw.dbManagement.model.Incidencia;

public class Chat {

	private List<Mensaje> mensajes = new ArrayList<Mensaje>();

	private boolean creacionEnProceso = false;
	private Incidencia inci;
	
	public Chat() {
		cargarMensajesInciales();
	}
	public List<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public void addMensaje(Mensaje m) {
		mensajes.add(m);
	}

	private void cargarMensajesInciales() {
		Mensaje m = new Mensaje(new Date(), "Hola, ¿en qué le podemos ayudar?", "bot");
		Mensaje m2 = new Mensaje(new Date(), "Puede usted escoger crear una incidencia o consultar las que ya has mandando", "bot");

		addMensaje(m);
		addMensaje(m2);
	}

	public void calcularRespuesta(String mensaje) {
		if(creacionEnProceso) {
			creacionIncidencia(mensaje);
		}
		else {
			// Si quiere crear
			String mensajeMayusculas = mensaje.toUpperCase();	
			String creacion = "crear creación creo";	
			String[] palabras = creacion.split("\\s+");
			for (String palabra : palabras) {
				if (mensajeMayusculas.contains(palabra) && !creacionEnProceso) {
					Mensaje m = new Mensaje(new Date(), "Ha seleccionado crear una nueva incidencia", "bot");
					addMensaje(m);
					m = new Mensaje(new Date(), "Indique el nombre de la incidencia", "bot");
					addMensaje(m);
					creacionEnProceso = true;
					inci = new Incidencia();
				}
			}
			if(!creacionEnProceso) {
				creacion = "lista listar listado";
				palabras = creacion.split("\\s+");
				for (String palabra : palabras) {
					if (mensajeMayusculas.contains(palabra)) {
						Mensaje m = new Mensaje(new Date(), "Para listar tus incidencias debes ir al apartado "
								+ "listado de incidencias que se encuentra en la barra superior", "bot");
						addMensaje(m);
						m = new Mensaje(new Date(), "¿Te podemos ayudar en alguna otra cosa?", "bot");
						addMensaje(m);
					}
				}
			}


		}

	}
	private void creacionIncidencia(String mensaje) {
		// TODO Auto-generated method stub
		
	}
}
