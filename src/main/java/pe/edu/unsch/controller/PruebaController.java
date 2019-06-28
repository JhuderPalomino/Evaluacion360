package pe.edu.unsch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")

public class PruebaController {
	@GetMapping("/prueba")
	public String index(Model model) {
		model.addAttribute("title", "Home | Evaluación 360°");
		return "views/admin/prueba/index";
	}

}
