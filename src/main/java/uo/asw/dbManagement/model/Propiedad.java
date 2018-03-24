package uo.asw.dbManagement.model;

import org.springframework.data.annotation.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import uo.asw.dbManagement.tipos.PropiedadTipos;

@Document(collection = "propiedades")
public class Propiedad {
	@Id
	private ObjectId id = new ObjectId();
	
//	@DBRef
	private PropiedadTipos propiedad;
	
	@DBRef
	private Incidencia incidencia;
	
	private Double valor;
	
	public Propiedad() {}

	public Propiedad(PropiedadTipos propiedad, Double valor) {
		super();
		this.propiedad = propiedad;
		this.valor = valor;
	}
	
	public Propiedad(String propiedad, Incidencia idIncidencia, Double valor) {
		super();
		this.propiedad = obtenerPropiedad(propiedad);
		this.incidencia = idIncidencia;
		this.valor = valor;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public PropiedadTipos getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(PropiedadTipos propiedad) {
		this.propiedad = propiedad;
	}

	public Incidencia getIdIncidencia() {
		return incidencia;
	}

	public void setIdIncidencia(Incidencia idIncidencia) {
		this.incidencia = idIncidencia;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	

	/*@Override
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
	}*/
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((propiedad == null) ? 0 : propiedad.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		if (propiedad != other.propiedad)
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "Propiedad [id=" + id + ", propiedad=" + propiedad + ", incidencia=" + incidencia + ", valor=" + valor
				+ "]";
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
