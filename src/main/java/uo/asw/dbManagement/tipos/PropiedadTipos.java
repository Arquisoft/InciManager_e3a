package uo.asw.dbManagement.tipos;

import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "propiedadTipos")
public enum PropiedadTipos {
	TEMPERATURA, 
	PRESION, 
	HUMEDAD,
	VELOCIDAD_VIENTO, 
	VELOCIDAD_CIRCULACION,
	VALOR_NO_ASIGNADO

}
