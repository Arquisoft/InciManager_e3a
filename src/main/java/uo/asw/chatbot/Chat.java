package uo.asw.chatbot;

import static org.hamcrest.CoreMatchers.hasItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uo.asw.dbManagement.model.Incidencia;
import uo.asw.inciManager.util.DateUtil;

public class Chat {

	private List<Mensaje> mensajes = new ArrayList<Mensaje>();

	private boolean creacionEnProceso = false;
	private int pasos=0;
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
			boolean loHePillado =false;
			
			// Si quiere crear
			String mensajeMayusculas = mensaje.toUpperCase();	
			String creacion = "CREAR CREACIÓN CREACION CREO";	
			String[] palabras = creacion.split("\\s+");
			for (String palabra : palabras) {
				if (mensajeMayusculas.contains(palabra) && !creacionEnProceso) {
					Mensaje m = new Mensaje(new Date(), "Ha seleccionado crear una nueva incidencia", "bot");
					addMensaje(m);
					 m = new Mensaje(new Date(), "Comenzamos el proceso de creación ...", "bot");
						addMensaje(m);
					m = new Mensaje(new Date(), "Indique el nombre de la incidencia", "bot");
					addMensaje(m);
					creacionEnProceso = true;
					loHePillado=true;
					inci = new Incidencia();
				}
			}
			if(!loHePillado) {
				creacion = "LISTA LISTAR LISTADO";
				palabras = creacion.split("\\s+");
				for (String palabra : palabras) {
					if (mensajeMayusculas.contains(palabra)) {
						Mensaje m = new Mensaje(new Date(), "Para listar tus incidencias debes ir al apartado "
								+ "listado de incidencias que se encuentra en la barra superior", "bot");
						addMensaje(m);
						m = new Mensaje(new Date(), "¿Te podemos ayudar en alguna otra cosa?", "bot");
						addMensaje(m);
						loHePillado=true;
					}
				}
			}
			if(!loHePillado) {
				creacion = "HOLA BUENAS";
				palabras = creacion.split("\\s+");
				for (String palabra : palabras) {
					if (mensajeMayusculas.contains(palabra)) {
						loHePillado=true;
					}
				}
			}
			//Este siempre al final
			if(!loHePillado) {
				Mensaje m = new Mensaje(new Date(), "Lo siento, no le he entendido", "bot");
				addMensaje(m);
			}


		}

	}
	private void creacionIncidencia(String mensaje) {
		switch (pasos) {
			case 0:
				inci.setNombreIncidencia(mensaje);
				Mensaje m = new Mensaje(new Date(), "¿Cuál es la descripción de esta incidencia?", "bot");
				addMensaje(m);
				pasos++;
				break;
			case 1:
				inci.setDescripcion(mensaje);
				Mensaje m2 = new Mensaje(new Date(), "Perfecto, introduce ahora la fecha de caducidad con el siguiente formato: dd/MM/yyyy", "bot");
				addMensaje(m2);
				m2 = new Mensaje(new Date(), "Siendo 'dd' el día del mes, 'MM' el mes del año y 'yyyy' el año", "bot");
				addMensaje(m2);
				pasos++;
				break;
			case 2:
				Date fecha = DateUtil.stringToDate(mensaje);
				if(fecha != null) {
					inci.setFechaEntrada(new Date());
					inci.setFechaCaducidad(fecha);
					Mensaje m3 = new Mensaje(new Date(), "Ya queda poco, escoja ahora una o varias de estas categorias:"
							+ "ACCIDENTE_CARRETERA, " + 
							"FUEGO, " + 
							"INUNDACION, " + 
							"ACCIDENTE_AEREO, " + 
							"METEOROLOGICA, " + 
							"VALOR_NO_ASIGNADO", "bot");
					addMensaje(m3);
					
					pasos++;
					break;
				}else {
					Mensaje m4 = new Mensaje(new Date(), "Parece que ha introducido la fecha con un formato no válido", "bot");
					addMensaje(m4);
					m4 = new Mensaje(new Date(), "Vuelva a introducir la fecha de caducidad que ha escogido para su incidencia", "bot");
					addMensaje(m4);
					break;
				}
		}
		
	}
}
