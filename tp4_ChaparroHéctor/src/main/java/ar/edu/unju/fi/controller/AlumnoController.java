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

import ar.edu.unju.fi.collections.CollectionAlumno;
import ar.edu.unju.fi.model.Alumno;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	
	@Autowired
	private Alumno alumno;
	
	
	@GetMapping("/listado")
	public String getAlumnosPage(Model model) {
		String mensaje = "";
		boolean exito = false;
		model.addAttribute("alumnos", CollectionAlumno.getAlumnos());
		model.addAttribute("titulo", "Alumnos");
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		return "alumnos";
	}
	
	
	@GetMapping("/formulario")
	public String getFormAlumnoPage(Model model) {
		boolean edicion = false;
		model.addAttribute("alumno", alumno);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nuevo Alumno");
		return "alumno-form";
	}
	
	@PostMapping("/guardar")
	public ModelAndView guardarAlumno(@ModelAttribute("alumno") Alumno alumno) {
		ModelAndView modelView = new ModelAndView("alumnos");
		String mensaje = "";
		Random random = new Random();
		alumno.setLU(random.nextInt(9000) + 1000L);
		boolean exito = CollectionAlumno.addAlumno(alumno);
		if (exito) {
			mensaje = "El alumno se agregó con exito!! ♥";
		} else {
			mensaje = "El alumno NO se pudo agregar :(";
		}
		modelView.addObject("alumnos", CollectionAlumno.getAlumnos());
		modelView.addObject("exito", exito);
		modelView.addObject("mensaje", mensaje);
		return modelView;
	}
	
	@GetMapping("/modificar/{dni}")
	public String getModificarAlumnoPage(Model model,RedirectAttributes redirectAttributes , @PathVariable(value="dni") String dni) {
		Alumno alumnoEncontrado = new Alumno();
		boolean edicion = true;
		alumnoEncontrado = CollectionAlumno.findAlumno(dni);
		
		if(alumnoEncontrado == null) {
			redirectAttributes.addFlashAttribute("alertMessage", "Alumno no encontrada. Redirigiendo a la lista.");
            return "redirect:/alumno/listado";
		} else {
			model.addAttribute("edicion", edicion);
			model.addAttribute("alumno", alumnoEncontrado);
			model.addAttribute("titulo", "Modificar Alumno");
			return "alumno-form";
		}
		
		
	}
	
	@PostMapping("/modificar")
	public String modificarAlumno(@ModelAttribute("alumno") Alumno alumno,Model model) {
		String mensaje ="";
		boolean exito = false;
		try {
			CollectionAlumno.updateAlumno(alumno);
			mensaje = "El alumno con DNI " + alumno.getDni() + " fue modificado con éxito";
			exito = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mensaje = e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("alumnos", CollectionAlumno.getAlumnos());
		model.addAttribute("titulo", "Alumnos");
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		return "alumnos";
	}
	
	@GetMapping("/eliminar/{dni}")
	public String eliminarAlumno(@PathVariable(value="dni") String dni) {
		CollectionAlumno.deleteAlumno(dni);
		return "redirect:/alumno/listado";
	}
	


}
