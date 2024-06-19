package ar.edu.unju.fi.collections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Alumno;



@Component
public class CollectionAlumno {
	
	
	private static ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
	
	
	/**
	 * Function que retorna el array de los alumnos
	 * @return(Alumno)
	 */
	public static ArrayList<Alumno> getAlumnos(){
		
		if(alumnos.isEmpty()) {
			alumnos.add(new Alumno("10", "Héctor", "Chaparro", "a1@hotmail.com", "1544767852", LocalDate.of(2000,11,30), "Av. Siempre Viva 426", 5643));
			alumnos.add(new Alumno("20", "Ezequiel", "Aparicio", "a2@hotmail.com", "154782594", LocalDate.of(2000,1,22), "Av. Salta 111", 2578));
			alumnos.add(new Alumno("30", "Emanuel", "Medina", "a3@hotmail.com", "154251493", LocalDate.of(2000,5,1), "Av. Exodo 783", 1249));
		}

		return alumnos;
	}
	
	/**
	 * Function que agrega un obj Alumno al array
	 * @param(Alumno)
	 */
	public static boolean addAlumno(Alumno alumno) {
		return alumnos.add(alumno);
	}
	
	/**
	 * Function que elimina un obj Alumno del array
	 * @param(Alumno.dni)
	 */
	public static void deleteAlumno(String dni) {
		Iterator<Alumno> iterator = alumnos.iterator();
		
		while(iterator.hasNext()) {
			if(iterator.next().getDni().equals(dni)) {
				iterator.remove();
				break;
			}
		}
	}
	
	/**
	 * Function que modifica un obj del array
	 * @param(Alumno)
	 */
	public static void updateAlumno(Alumno alumno)throws Exception {
		boolean encontrado = false;
		alumnos = getAlumnos();
		try {
			for(Alumno a: alumnos) {
				if(a.getDni().equals( (alumno.getDni() ))) {
					a.setNombre(alumno.getNombre());
					a.setApellido(alumno.getApellido());
					a.setEmail(alumno.getEmail());
					a.setTelefono(alumno.getTelefono());
					if(alumno.getFechaDeNacimiento() != null) {
						a.setFechaDeNacimiento(alumno.getFechaDeNacimiento());
					}
					a.setDomicilio(alumno.getDomicilio());
					a.setLU(alumno.getLU());
					encontrado= true;
					break;
				}
				
			}
			if (!encontrado) {
				
				throw new Exception ("El alumno con DNI " + alumno.getDni() + " no existe");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Function que busca un obj por su dni y lo retonrna
	 * @param(Alumno.dni)
	 * @return (Alumno)
	 */
	public static Alumno findAlumno(String dni) {
		
		Predicate<Alumno> filterDNI = c -> c.getDni().equals(dni);
		Optional<Alumno> alumno = alumnos.stream().filter(filterDNI).findFirst();
		if(alumno.isPresent()) {
			return alumno.get();
		} else {
			return null;
		}
	}
	

}
