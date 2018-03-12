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

import uo.asw.dbManagement.tipos.CategoriaTipos;

@Entity
@Table (name = "TCATEGORIAS")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING) private CategoriaTipos categoria;
	
	private Double valor;
	
	@Column(name = "id_incidencia")
	@ManyToOne
	private Long idIncidenciaC;
	
	public Categoria() {}


	
	

}
