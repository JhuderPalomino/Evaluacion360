package pe.edu.unsch.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unsch.entities.Modulo;
import pe.edu.unsch.service.ModuloServie;
import pe.edu.unsch.util.DataTablesParam;
import pe.edu.unsch.util.DataTablesParamUtility;
import pe.edu.unsch.util.ManagerDatatables;

@Controller
@RequestMapping("/admin")
public class ModuloController {
	
	@Autowired
	private ModuloServie moduloService;
	
	@GetMapping("/modulo")
	public String mainGet(Model model) {
		model.addAttribute("titulo", "Módulo | Evaluación 360°");
		return "views/admin/modulo/index";
	}
	
	@GetMapping("/modulosJSON")
	@ResponseBody
	public ManagerDatatables obtenerModulosJSON(HttpServletRequest request, HttpServletResponse response) {
		return obtenerModuloDatatables(request, response);
	}
	
	private ManagerDatatables obtenerModuloDatatables(HttpServletRequest request, HttpServletResponse response) {

		System.out.print("Que pasa aquí?");
		ManagerDatatables managerDatatables = new ManagerDatatables();
		DataTablesParam dataTablesParam = DataTablesParamUtility.getParam(request);
		List<Modulo> modulos = moduloService.encontrarTodo();

		managerDatatables.setiTotalRecords(0);

		for (int i = 0; i <= modulos.size() - 1; ++i) {
			Modulo p = modulos.get(i);
			if (String.valueOf(p.getIdmodulo()).toLowerCase().contains(dataTablesParam.sSearch.toLowerCase())
					|| p.getNombre().toLowerCase().contains(dataTablesParam.sSearch.toLowerCase())
					|| p.getEstado().toString().toLowerCase().contains(dataTablesParam.sSearch.toLowerCase())) {
			} else {
				modulos.remove(i);
				i = i - 1;
			}
		}

		final int sortColumnIndex = dataTablesParam.iSortColumnIndex;
		System.out.println(sortColumnIndex);
		final int sortDirection = dataTablesParam.sSortDirection.equals("desc") ? -1 : 1;
		System.out.println(sortDirection);

		//collection clase
		//comparator interfaz
		Collections.sort(modulos, new Comparator<Modulo>() {
			@Override
			public int compare(Modulo o1, Modulo o2) {
				switch (sortColumnIndex) {
				case 0:
					return ((Integer) o1.getIdmodulo()).compareTo(o2.getIdmodulo()) * sortDirection;
				case 1:
					return o1.getNombre().toLowerCase().compareTo(o2.getNombre().toLowerCase()) * sortDirection;
				case 2:
					return o1.getEstado().toString().toLowerCase().compareTo(o2.getEstado().toString().toLowerCase())
							* sortDirection;
				}
				return 0;
			}
		});

		managerDatatables.setiTotalDisplayRecords(modulos.size());

		if (modulos.size() < dataTablesParam.iDisplayStart + dataTablesParam.iDisplayLength) {
			modulos = modulos.subList(dataTablesParam.iDisplayStart, modulos.size());
		} else {
			modulos = modulos.subList(dataTablesParam.iDisplayStart,
					dataTablesParam.iDisplayStart + dataTablesParam.iDisplayLength);
		}

		managerDatatables.setsEcho(dataTablesParam.sEcho);
		managerDatatables.setAaData(modulos);

		return managerDatatables;
	}

}
