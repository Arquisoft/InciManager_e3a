package uo.asw;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RecibirIncidenciaController {
	
	@RequestMapping(value = "/inci", method = RequestMethod.POST)
	public ResponseEntity<Incidencia> update(@RequestBody Map<String, Object> inci) {
		String nombre, descripcion;
		HttpStatus respuesta = HttpStatus.OK;
		Incidencia i = new Incidencia ("nombre_no_definido", "descripcion_no_definida");
		nombre = (String)inci.get("nombre");
		descripcion = (String)inci.get("descripcion");
		if (nombre != null && nombre != "" ) {
			i.setNombre(nombre);
			respuesta = HttpStatus.NOT_ACCEPTABLE;
		}
		if (descripcion != null && descripcion != "") {
			i.setDescripcion(descripcion + " prueba");
			respuesta = HttpStatus.NOT_ACCEPTABLE;
		}
	    
	   return new ResponseEntity<Incidencia>(i, respuesta);
	}
	
	

}
