package uo.asw.dbManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uo.asw.dbManagement.tipos.PropiedadTipos;

@Entity
@Table (name = "TPROPIEDADES")
public class Propiedad {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING) private PropiedadTipos propiedad;
	
	@Column(name = "id_incidencia")
	@ManyToOne
	private Long idIncidencia;
	

}
