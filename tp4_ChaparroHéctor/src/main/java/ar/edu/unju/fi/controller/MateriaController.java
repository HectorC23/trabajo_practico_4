package ar.edu.unju.fi.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.fi.collections.CollectionCarrera;
import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.collections.CollectionMateria;
import ar.edu.unju.fi.model.Materia;

@Controller
@RequestMapping("/materia")
public class MateriaController {
	
	@Autowired
	private Materia materia;

	@GetMapping("/listado")
	public String getMateriaPage(Model model) {
		model.addAttribute("materias", CollectionMateria.getMaterias());
		model.addAttribute("titulo", "Materias");
		return "materias";
	}

	@GetMapping("/formulario")
	public String getFormMateriaPage(Model model){
		boolean edicion = false;
		model.addAttribute("materia", materia);
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nueva Materia");
		return "materia-form";
	}

	@PostMapping("/guardar")
	public ModelAndView guardarMateria(@ModelAttribute("materia") Materia materia,
										@RequestParam("legajo") long legajo,
										@RequestParam("codCarrera") long codCarrera) {
		
		System.out.println(codCarrera);
		System.out.println(legajo);
		System.out.println(CollectionDocente.findDocente(legajo));
		System.out.println(CollectionCarrera.searchCarrera(codCarrera));
		
		ModelAndView modelView = new ModelAndView("materias");
		
		Random random = new Random();
		materia.setCodigo(random.nextLong(9000)+1000);
		materia.setDocente(CollectionDocente.findDocente(legajo));
		materia.setCarrera(CollectionCarrera.searchCarrera(codCarrera));
		
		CollectionMateria.addMateria(materia);
		modelView.addObject("materias",CollectionMateria.getMaterias());
		
		return modelView;
		
	}

	@GetMapping("/modificar/{codigo}")
	public String getModificarMateriaPage(Model model,RedirectAttributes redirectAttributes, 
											@PathVariable(value="codigo") long codigo) {
		
		Materia materiaEncontrada = new Materia();
		boolean edicion = true;
		materiaEncontrada = CollectionMateria.findMateria(codigo);
		
		if(materiaEncontrada == null) {
			System.out.println("1");
			redirectAttributes.addFlashAttribute("alertMessage", "Materia no encontrada. Redirigiendo a la lista.");
            return "redirect:/materia/listado";
		} else {
			
			model.addAttribute("edicion", edicion);
			model.addAttribute("materia", materiaEncontrada);
			model.addAttribute("docentes", CollectionDocente.getDocentes());
			model.addAttribute("carreras", CollectionCarrera.getCarreras());
			model.addAttribute("titulo", "Modificar Materia");
			return "materia-form";
		}	
	}

	@PostMapping("/modificar")
	public String modificarMateria(@ModelAttribute("materia") Materia materia,
									@RequestParam("legajo") long legajo,
									@RequestParam("codCarrera") long codCarrera) {
									
		materia.setDocente(CollectionDocente.findDocente(legajo));
		materia.setCarrera(CollectionCarrera.searchCarrera(codCarrera));
		System.out.println(materia);
		CollectionMateria.updateMateria(materia);
		return "redirect:/materia/listado";
	}
	
	@GetMapping("/eliminar/{codigo}")
	public String eliminarMateria(@PathVariable(value="codigo") long codigo) {
		CollectionMateria.deleteMateria(codigo);
		return "redirect:/materia/listado";
	}


}
