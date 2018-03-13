package uo.asw.dbManagement.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "TINCIDENCIAS")
public class Incidencia {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "nombre_incidencia")
	private String nombreIncidencia;
	private String descripcion;
	private String latitud;
	private String longitud;
	private String estado;
	@Column(name = "fecha_entrada")
	@Temporal(TemporalType.DATE)
	private Date fechaEntrada;
	@Column(name = "fecha_caducidad")
	@Temporal(TemporalType.DATE)
	private Date fechaCaducidad;
	
	@Column(name = "id_agente")
	@ManyToOne
	private Long idAgente;
	
	@OneToMany(mappedBy = "idIncidencia")
	private Set<Propiedad> propiedades;
	
	@OneToMany(mappedBy = "idIncidenciaC")
	private Set<Categoria> categorias;
	
	public Incidencia() {}

	public Incidencia(String nombreIncidencia, String descripcion, 
			String latitud, String longitud, String estado,
			Date fechaEntrada, Date fechaCaducidad, Long idAgente, 
			Set<Propiedad> propiedades,
			Set<Categoria> categorias) {
		super();
		this.nombreIncidencia = nombreIncidencia;
		this.descripcion = descripcion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.estado = estado;
		this.fechaEntrada = fechaEntrada;
		this.fechaCaducidad = fechaCaducidad;
		this.idAgente = idAgente;
		this.propiedades = propiedades;
		this.categorias = categorias;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreIncidencia() {
		return nombreIncidencia;
	}

	public void setNombreIncidencia(String nombreIncidencia) {
		this.nombreIncidencia = nombreIncidencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public Long getIdAgente() {
		return idAgente;
	}

	public void setIdAgente(Long idAgente) {
		this.idAgente = idAgente;
	}

	public Set<Propiedad> getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(Set<Propiedad> propiedades) {
		this.propiedades = propiedades;
	}

	public Set<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaEntrada == null) ? 0 : fechaEntrada.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idAgente == null) ? 0 : idAgente.hashCode());
		result = prime * result + ((nombreIncidencia == null) ? 0 : nombreIncidencia.hashCode());
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
		Incidencia other = (Incidencia) obj;
		if (fechaEntrada == null) {
			if (other.fechaEntrada != null)
				return false;
		} else if (!fechaEntrada.equals(other.fechaEntrada))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idAgente == null) {
			if (other.idAgente != null)
				return false;
		} else if (!idAgente.equals(other.idAgente))
			return false;
		if (nombreIncidencia == null) {
			if (other.nombreIncidencia != null)
				return false;
		} else if (!nombreIncidencia.equals(other.nombreIncidencia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Incidencia [id=" + id + ", nombreIncidencia=" 
	+ nombreIncidencia + ", descripcion=" + descripcion
				+ ", latitud=" + latitud + ", longitud=" + longitud 
				+ ", estado=" + estado + ", fechaEntrada="
				+ fechaEntrada + ", fechaCaducidad=" + fechaCaducidad 
				+ ", idAgente=" + idAgente + ", propiedades="
				+ propiedades + ", categorias=" + categorias + "]";
	}

	
	

}
