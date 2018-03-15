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
	
	private Double valor;
	
	public Propiedad() {}

	public Propiedad(PropiedadTipos propiedad, Long idIncidencia, Double valor) {
		super();
		this.propiedad = propiedad;
		this.idIncidencia = idIncidencia;
		this.valor = valor;
	}
	
	public Propiedad(String propiedad, Long idIncidencia, Double valor) {
		super();
		this.propiedad = obtenerPropiedad(propiedad);
		this.idIncidencia = idIncidencia;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PropiedadTipos getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(PropiedadTipos propiedad) {
		this.propiedad = propiedad;
	}

	public Long getIdIncidencia() {
		return idIncidencia;
	}

	public void setIdIncidencia(Long idIncidencia) {
		this.idIncidencia = idIncidencia;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idIncidencia == null) ? 0 : idIncidencia.hashCode());
		result = prime * result + ((propiedad == null) ? 0 : propiedad.hashCode());
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
		Propiedad other = (Propiedad) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idIncidencia == null) {
			if (other.idIncidencia != null)
				return false;
		} else if (!idIncidencia.equals(other.idIncidencia))
			return false;
		if (propiedad != other.propiedad)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Propiedad [id=" + id + ", propiedad=" 
				+ propiedad + ", idIncidencia=" + idIncidencia + ", valor="
				+ valor + "]";
	}
	
	/**
	 * Devuelve un tipo de propiedad según la propiedad pasada como 
	 * parámetro
	 * @param propiedad
	 * @return PropiedadTipos
	 */
	private PropiedadTipos obtenerPropiedad(String propiedad) {
		if (propiedad.toUpperCase().equals("TEMPERATURA"))
			return PropiedadTipos.TEMPERATURA;
		if (propiedad.toUpperCase().equals("PRESION"))
			return PropiedadTipos.PRESION;
		if (propiedad.toUpperCase().equals("HUMEDAD"))
			return PropiedadTipos.HUMEDAD;
		if (propiedad.toUpperCase().equals("VELOCIDAD_VIENTO"))
			return PropiedadTipos.VELOCIDAD_VIENTO;
		if (propiedad.toUpperCase().equals("VELOCIDAD_CIRCULACION"))
			return PropiedadTipos.VELOCIDAD_CIRCULACION;
		return PropiedadTipos.VALOR_NO_ASIGNADO;
	}

}
