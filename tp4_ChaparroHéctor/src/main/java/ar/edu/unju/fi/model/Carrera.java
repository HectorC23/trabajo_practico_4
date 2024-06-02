package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

@Component
public class Carrera {
	
	private long codigo;
	private String nombre;
	private byte cantAnios;
	private boolean estado;
	
	public Carrera() {}
	
	public Carrera(long codigo, String nombre, byte cantAnios, boolean estado) {
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

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
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

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	

}
