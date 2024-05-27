package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String getIndexPage() {
		return "index";
	}
	
	
	@GetMapping("/home")
	public String getIndexHomer() {
		return "index";
	}
	

}
