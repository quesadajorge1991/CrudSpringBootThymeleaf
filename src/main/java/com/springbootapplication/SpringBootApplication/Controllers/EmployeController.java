package com.springbootapplication.SpringBootApplication.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbootapplication.SpringBootApplication.Entity.Department;
import com.springbootapplication.SpringBootApplication.Entity.Employee;
import com.springbootapplication.SpringBootApplication.Repository.DepartmentRepository;
import com.springbootapplication.SpringBootApplication.Repository.EmployeeRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/employe")
public class EmployeController {

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping(value = "/employes")
	public String departments(Model model) {
		model.addAttribute("employes", employeeRepository.findAll());
		return "Employe/employes";
	}

	@GetMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("employe", new Employee());
		model.addAttribute("departments", departmentRepository.findAll());
		return "Employe/addemploye";
	}

	@PostMapping(value = "/adddemploye")
	public String adddemploye(@Valid @ModelAttribute("employe") Employee employee, BindingResult bindingResult,
			@RequestParam(value = "department") int id, RedirectAttributes redirectAttributes, ModelMap model) {

		// validar si tiene algun error, con bindingResult

		if (bindingResult.hasErrors()) {
			model.addAttribute("departments", departmentRepository.findAll());
			return "Employe/addemploye";
		}

		try {

			if (employee.getEid() != 0) {
				employeeRepository.save(
						new Employee(employee.getEid(), employee.getEname(), employee.getSalary(),  new Department(id)));
				redirectAttributes.addFlashAttribute("msgtipo", "success");
				redirectAttributes.addFlashAttribute("msgtitu", "Información");
				redirectAttributes.addFlashAttribute("msgbody", "Empleado modificado correctamente");

			} else {
				employeeRepository.save(
						new Employee(employee.getEname(), employee.getSalary(),  new Department(id)));
				redirectAttributes.addFlashAttribute("msgtipo", "success");
				redirectAttributes.addFlashAttribute("msgtitu", "Información");
				redirectAttributes.addFlashAttribute("msgbody", "Empleado agregado correctamente");
			}

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al agregar Empleado");
		}

		return "redirect:/employe/employes";
	}

	@GetMapping(value = "/deleteemploye/{id}")
	public String deletedepartment(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {

		// validar si tiene algun error, con bindingResult

		try {
			employeeRepository.delete(new Employee(id));
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Información");
			redirectAttributes.addFlashAttribute("msgbody", "Empleado eliminado correctamente");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al eliminar Empleado");
		}

		return "redirect:/employe/employes";
	}

	@GetMapping(value = "/update/{eid}")
	public String update(@Valid @ModelAttribute("employe") Employee employee, BindingResult bindingResult,
			Model model) {

		try {

			List<Department> list = departmentRepository.findAll();
			Employee employee2 = employeeRepository.findById(employee.getEid()).get();

			list.remove(employee2.getDepartment());
			list.add(0, employee2.getDepartment());

			if (bindingResult.hasErrors()) {

				model.addAttribute("departments", list);
			}

			model.addAttribute("employe", employee2);

			model.addAttribute("departments", list);

		} catch (Exception e) {

			System.err.println(e.getMessage());
		}

		return "Employe/updateemploye";
	}

}
