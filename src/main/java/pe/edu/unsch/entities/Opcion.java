package pe.edu.unsch.entities;
// Generated 27-may-2019 23:22:39 by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Opcion generated by hbm2java
 */
@Entity
@Table(name = "opcion", catalog = "evaluacion360bd")
public class Opcion implements java.io.Serializable {

	private Integer idopcion;
	private Submodulo submodulo;
	private String nombre;
	private Byte estado;
	private Set<PerfilOpcion> perfilOpcions = new HashSet<PerfilOpcion>(0);

	public Opcion() {
	}

	public Opcion(Submodulo submodulo) {
		this.submodulo = submodulo;
	}

	public Opcion(Submodulo submodulo, String nombre, Byte estado, Set<PerfilOpcion> perfilOpcions) {
		this.submodulo = submodulo;
		this.nombre = nombre;
		this.estado = estado;
		this.perfilOpcions = perfilOpcions;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idopcion", unique = true, nullable = false)
	public Integer getIdopcion() {
		return this.idopcion;
	}

	public void setIdopcion(Integer idopcion) {
		this.idopcion = idopcion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idsubmodulo", nullable = false)
	public Submodulo getSubmodulo() {
		return this.submodulo;
	}

	public void setSubmodulo(Submodulo submodulo) {
		this.submodulo = submodulo;
	}

	@Column(name = "nombre", length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "estado")
	public Byte getEstado() {
		return this.estado;
	}

	public void setEstado(Byte estado) {
		this.estado = estado;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "opcion")
	public Set<PerfilOpcion> getPerfilOpcions() {
		return this.perfilOpcions;
	}

	public void setPerfilOpcions(Set<PerfilOpcion> perfilOpcions) {
		this.perfilOpcions = perfilOpcions;
	}

}
