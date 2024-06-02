package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;



@Component
public class CollectionCarrera {
	
	
	private static ArrayList<Carrera> carreras = new ArrayList<Carrera>();
	
	
	
	/**
	 * Function que retorna el array de las carreras
	 * @return(Carrera)
	 */
	public static ArrayList<Carrera> getCarreras(){
		
		if(carreras.isEmpty()) {
			carreras.add(new Carrera(1, "APU", (byte)3, true));
			carreras.add(new Carrera(2, "Ingeniería Informatica", (byte)5, true));
			carreras.add(new Carrera(3, "Ingeniería De Minas", (byte)5, false));
		}

		return carreras;
	}
	
	/**
	 * Function que agrega un obj Carrera al array
	 * @param(Carrera)
	 */
	public static void addCarrera(Carrera carrera) {
		carreras.add(carrera);
	}
	
	/**
	 * Function que elimina un obj Carrera del array
	 * @param(Carrera.codigo)
	 */
	public static void deleteCarrera(long codigoCarrera) {
		Iterator<Carrera> iterator = carreras.iterator();
		
		while(iterator.hasNext()) {
			if(iterator.next().getCodigo() == codigoCarrera) {
				iterator.remove();
				break;
			}
		}
	}
	
	/**
	 * Function que modifica un obj del array
	 * @param(Carrera)
	 */
	public static void updateCarrera(Carrera carrera) {
		for(Carrera carre: carreras) {
			if(carre.getCodigo() == carrera.getCodigo()) {
				carre.setNombre(carrera.getNombre());
				carre.setCantAnios(carrera.getCantAnios());
				carre.setEstado(carrera.getEstado());
				break;
			} else {
				System.out.println("Carrera no encontrada // No existe");
			}
		}
	}
	
	/**
	 * Function que busca un obj por su codigo y lo retonrna
	 * @param(Carrera.codigo)
	 * @return (Carrera)
	 */
	public static Carrera searchCarrera(long codigo) {
		
		Predicate<Carrera> filterCodigo = c -> c.getCodigo() == codigo;
		Optional<Carrera> carrera = carreras.stream().filter(filterCodigo).findFirst();
		if(carrera.isPresent()) {
			return carrera.get();
		} else {
			return null;
		}
	}

}
