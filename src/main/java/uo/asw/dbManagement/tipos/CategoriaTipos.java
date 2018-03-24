package uo.asw.dbManagement.tipos;

import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "categoriaTipos")
public enum CategoriaTipos {
	ACCIDENTE_CARRETERA,
	FUEGO, 
	INUNDACION,
	ACCIDENTE_AEREO,
	METEOROLOGICA, 
	VALOR_NO_ASIGNADO

}
