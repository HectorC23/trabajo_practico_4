package ar.edu.unju.fi.controller;


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

import ar.edu.unju.fi.collections.CollectionCarrera;
import ar.edu.unju.fi.model.Carrera;



@Controller
@RequestMapping("/carrera")
public class CarreraController {
	
	@Autowired
	private Carrera carrera;
	
	@GetMapping("/listado")
	public String getCarrerasPage(Model model) {
		
		String mensaje = "";
		boolean exito = false;
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("titulo", "Carreras");
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		return "carreras";
	}
	
	
	@GetMapping("/formulario")
	public String getFormCarreraPage(Model model) {
		boolean edicion = false;
		model.addAttribute("carrera", carrera);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nueva Carrera");
		return "carrera-form";
	}
	
	@PostMapping("/guardar")
	public ModelAndView guardarCarrera(@ModelAttribute("carrera") Carrera carrera) {
		ModelAndView modelView = new ModelAndView("carreras");
		String mensaje = "";
		carrera.setEstado(true);
		boolean exito = CollectionCarrera.addCarrera(carrera);
		if (exito) {
			mensaje = "La Carrera se agregó con exito!! ♥";
		} else {
			mensaje = "La carrera NO se pudo agregar :(";
		}
		modelView.addObject("carreras", CollectionCarrera.getCarreras());
		modelView.addObject("exito", exito);
		modelView.addObject("mensaje", mensaje);
		return modelView;
	}
	
	@GetMapping("/modificar/{codigo}")
	public String getModificarCarreraPage(Model model,RedirectAttributes redirectAttributes , @PathVariable(value="codigo") long codigo) {
		Carrera carreraEncontrada = new Carrera();
		boolean edicion = true;
		carreraEncontrada = CollectionCarrera.searchCarrera(codigo);
		
		if(carreraEncontrada == null) {
			redirectAttributes.addFlashAttribute("alertMessage", "Carrera no encontrada. Redirigiendo a la lista.");
            return "redirect:/carrera/listado";
		} else {
			model.addAttribute("edicion", edicion);
			model.addAttribute("carrera", carreraEncontrada);
			model.addAttribute("titulo", "Modificar Carrera");
			return "carrera-form";
		}
		
		
	}
	
	@PostMapping("/modificar")
	public String modificarCarrera(@ModelAttribute("carrera") Carrera carrera,Model model) {
		String mensaje ="";
		boolean exito = false;
		try {
			CollectionCarrera.updateCarrera(carrera);
			mensaje = "La carrera con código " + carrera.getCodigo() + " fue modificada con éxito";
			exito = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mensaje = e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("titulo", "Carreras");
		return "carreras";
	}
	
	@GetMapping("/eliminar/{codigo}")
	public String eliminarCarrera(@PathVariable(value="codigo") long codigo) {
		CollectionCarrera.deleteCarrera(codigo);
		return "redirect:/carrera/listado";
	}

}
