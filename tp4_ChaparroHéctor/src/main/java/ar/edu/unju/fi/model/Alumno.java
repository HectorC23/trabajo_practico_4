package ar.edu.unju.fi.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class Alumno {
	
	
	private String dni;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private LocalDate fechaDeNacimiento;
	private String domicilio;
	private long LU;
	

	public Alumno() {}
	
	public Alumno(String dni, String nombre, String apellido, String email, String telefono,
			LocalDate fechaDeNacimiento, String domicilio, long lU) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.domicilio = domicilio;
		LU = lU;
	}
	
	@Override
	public String toString() {
		return "Alumno [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ ", telefono=" + telefono + ", fechaDeNacimiento=" + fechaDeNacimiento + ", domicilio=" + domicilio
				+ ", LU=" + LU + "]";
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public String getFechaFormateada() {
        return this.fechaDeNacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
	public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public long getLU() {
		return LU;
	}
	public void setLU(long lU) {
		LU = lU;
	}
	
	
	

}
