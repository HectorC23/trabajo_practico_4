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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.model.Docente;

@Controller
@RequestMapping("/docente")
public class DocenteController {
	
	@Autowired
	private Docente docente;
	
	@GetMapping("/listado")
	public String getAlumnosPage(Model model) {
		String mensaje = "";
		boolean exito = false;
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("titulo", "Docentes");
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		return "docentes";
	}
	
	@GetMapping("/formulario")
	public String getFormularioPage(Model model){
		boolean edicion = false;
		model.addAttribute("docente", docente);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nuevo Docente");
		return "docente-form";
	}
	
	@PostMapping("/guardar")
	public ModelAndView guardarDocente(@ModelAttribute("docente") Docente docente) {
		ModelAndView modelView = new ModelAndView("docentes");
		String mensaje = "";
		Random random = new Random();
		docente.setLegajo(random.nextInt(9000)+1000);
		boolean exito = CollectionDocente.addDocente(docente);
		if (exito) {
			mensaje = "El docente se agregó con exito!! ♥";
		} else {
			mensaje = "El docente NO se pudo agregar :(";
		}
		modelView.addObject("docentes",CollectionDocente.getDocentes());
		modelView.addObject("exito", exito);
		modelView.addObject("mensaje", mensaje);
		return modelView;
	}
	
	@GetMapping("/modificar/{legajo}")
	public String getModificarDocentePage(Model model,RedirectAttributes redirectAttributes , @PathVariable(value="legajo") long legajo) {
		Docente docenteEncontrado = new Docente();
		boolean edicion = true;
		docenteEncontrado = CollectionDocente.findDocente(legajo);
		
		if(docenteEncontrado == null) {
			redirectAttributes.addFlashAttribute("alertMessage", "Docente no encontrada. Redirigiendo a la lista.");
            return "redirect:/alumno/listado";
		} else {
			model.addAttribute("edicion", edicion);
			model.addAttribute("docente", docenteEncontrado);
			model.addAttribute("titulo", "Modificar Docente");
			return "docente-form";
		}	
	}
	
	@PostMapping("/modificar")
	public String modificarDocente(@ModelAttribute("docente") Docente docente, Model model) {
		String mensaje ="";
		boolean exito = false;
		try {
			CollectionDocente.updateDocente(docente);
			mensaje = "El docente con legajo " + docente.getLegajo() + " fue modificada con éxito";
			exito = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mensaje = e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("titulo", "Docentes");
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		return "docentes";
	}
	
	@GetMapping("/eliminar/{legajo}")
	public String eliminarAlumno(@PathVariable(value="legajo") long legajo) {
		CollectionDocente.deleteDocente(legajo);
		return "redirect:/docente/listado";
	}
	
}
