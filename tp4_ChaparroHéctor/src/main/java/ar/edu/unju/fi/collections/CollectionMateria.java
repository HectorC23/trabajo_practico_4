package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Predicate;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;

public class CollectionMateria {
	
	private static ArrayList<Materia> materias = new ArrayList<Materia>();
	private static ArrayList<Docente> docentes = CollectionDocente.getDocentes();
	private static ArrayList<Carrera> carreras = CollectionCarrera.getCarreras();
	
	/**
	 * READ
	 * Function que inicializa, carga y retorna el array materias con obj Materia
	 * cargados
	 * @return(ArrayList<Materia>)
	 */
	public static ArrayList<Materia> getMaterias(){
		
		if (materias.isEmpty()) {
			materias.add(new Materia(111, "Programación Visual",(byte)2, 54, "Virtual", docentes.get(0) , carreras.get(0)));
			materias.add(new Materia(222, "Programación Orientada a Objetos",(byte) 2, 80, "Virtual", docentes.get(1) , carreras.get(2)));
			materias.add(new Materia(333, "Programación y Servicios Web", (byte)3, 120, "Presencial-Virtual", docentes.get(2) , carreras.get(0)));
		}
		
		return materias;
	}
	
	/**
	 * ADD
	 * Function que agrega una Materia al Array de materias
	 * @param(Materia)
	 */
	public static void addMateria(Materia materia) {
		materias.add(materia);
	}
	
	/**
	 * DELETE
	 * Funcion que elimina una Materia del Array de materias
	 * @param(codigo)
	 */
	public static void deleteMateria(long codigo) {
		Iterator<Materia> iterator = materias.iterator();
		
		while(iterator.hasNext()) {
			if(iterator.next().getCodigo() == codigo) {
				iterator.remove();
				break;
			}
		}
	}
	
	/**
	 * UPDATE
	 * Function que modifica una Materia del Array materias
	 * @param(Materia)
	 */
	public static void updateMateria(Materia materia) {
		for(Materia m: materias) {
			if (m.getCodigo() == materia.getCodigo()) {
				m.setNombre(materia.getNombre());
				m.setCurso(materia.getCurso());
				m.setHoras(materia.getHoras());
				m.setModalidad(materia.getModalidad());
				m.setDocente(materia.getDocente());
				m.setCarrera(materia.getCarrera());
				break;
			} else {
				System.out.println("Docente no encontrado // No existe");
			}
			
		}
	}
	
	/**
	 * Find
	 * Function que busca en el array materias(por su codigo) y lo retorna
	 * @param(codigo)
	 * @return(Materia)
	 */
	public static Materia findMateria(long codigo) {
		
		Predicate<Materia> filterCodigo = m -> m.getCodigo() == codigo;
		Optional<Materia> materia = materias.stream().filter(filterCodigo).findFirst();
		
		if(materia.isPresent()) {
			return materia.get();
		} else {
			return null;
		}
	}

}
