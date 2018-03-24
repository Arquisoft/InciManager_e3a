package uo.asw.dbManagement.tipos;

import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "estadoTipos")
public enum EstadoTipos {
	ABIERTA, 
	EN_PROCESO, 
	CERRADA, 
	ANULADA

}
