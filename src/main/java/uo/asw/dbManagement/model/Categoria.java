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
	
	@Column(name = "id_incidencia")
	@ManyToOne
	private Long idIncidenciaC;
	
	public Categoria() {}

	public Categoria(CategoriaTipos categoria, Long idIncidenciaC) {
		super();
		this.categoria = categoria;
		this.idIncidenciaC = idIncidenciaC;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CategoriaTipos getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaTipos categoria) {
		this.categoria = categoria;
	}

	public Long getIdIncidenciaC() {
		return idIncidenciaC;
	}

	public void setIdIncidenciaC(Long idIncidenciaC) {
		this.idIncidenciaC = idIncidenciaC;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idIncidenciaC == null) ? 0 : idIncidenciaC.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (categoria != other.categoria)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idIncidenciaC == null) {
			if (other.idIncidenciaC != null)
				return false;
		} else if (!idIncidenciaC.equals(other.idIncidenciaC))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", categoria=" + categoria 
				+ ", idIncidenciaC=" + idIncidenciaC + "]";
	}

	
	
	

}
