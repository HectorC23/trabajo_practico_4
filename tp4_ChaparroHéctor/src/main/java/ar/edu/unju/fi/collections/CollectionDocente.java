package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Docente;

@Component
public class CollectionDocente {
	
	
	private static ArrayList<Docente> docentes = new ArrayList<Docente>();

	/**
	 * Read
	 * Function que inicializa y retorna el array docentes
	 * @return(ArrayList<Docente>)
	 */
	public static ArrayList<Docente> getDocentes(){
		
		if(docentes.isEmpty()) {
			docentes.add(new Docente(1, "Carolina", "Apaza", "carolinaApaza1@gmail.com", "154765214"));
			docentes.add(new Docente(2, "Ariel ", "Vega", "arielVega2@gmail.com", "155412097"));
			docentes.add(new Docente(3, "Gustavo ", "Sosa", "gustavoSosa3@gmail.com", "156325874"));
		}
		
		return docentes;
	}
	
	/**
	 * Function que agrega Docentes al Array de docentes
	 * @param(Docente)
	 */
	public static boolean addDocente(Docente docente) {
		return docentes.add(docente);
	}
	
	/**
	 * Delete
	 * Funcion que elimina un Docente del Array de docentes
	 * @param(legajo)
	 */
	public static void deleteDocente(long legajo) {
		Iterator<Docente> iterator = docentes.iterator();
		
		while(iterator.hasNext()) {
			if(iterator.next().getLegajo() == legajo) {
				iterator.remove();
				break;
			}
		}
	}
	
	/**
	 * Update
	 * Function que modifica un Docente del Array docentes
	 * @param(Docente)
	 */
	public static void updateDocente(Docente docente)throws Exception {
		boolean encontrado = false;
		try {
			for(Docente d: docentes) {
				if (d.getLegajo() == docente.getLegajo()) {
					d.setNombre(docente.getNombre());
					d.setApellido(docente.getApellido());
					d.setEmail(docente.getEmail());
					d.setTelefono(docente.getTelefono());
					encontrado = true;
					break;
				}	
			}
			if (!encontrado) {
				throw new Exception ("El docente con legajo " + docente.getLegajo() + " no existe");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Find
	 * Function que busca en Docente(por su legajo) y lo retorna
	 * @param(legajo)
	 * @return(Docente)
	 */
	public static Docente findDocente(long legajo) {
		
		Predicate<Docente> filterLegajo = c -> c.getLegajo() == legajo;
		Optional<Docente> docente = docentes.stream().filter(filterLegajo).findFirst();
		
		if(docente.isPresent()) {
			return docente.get();
		} else {
			return null;
		}
	}
	
}
