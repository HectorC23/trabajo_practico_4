package ar.edu.unju.fi.model;

public class Carrera {
	
	private String codigo;
	private String nombre;
	private byte cantAnios;
	private String estado;
	
	public Carrera(String codigo, String nombre, byte cantAnios, String estado) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.cantAnios = cantAnios;
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Carrera [codigo=" + codigo + ", nombre=" + nombre + ", cantAnios=" + cantAnios + ", estado=" + estado
				+ "]";
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte getCantAnios() {
		return cantAnios;
	}

	public void setCantAnios(byte cantAnios) {
		this.cantAnios = cantAnios;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
