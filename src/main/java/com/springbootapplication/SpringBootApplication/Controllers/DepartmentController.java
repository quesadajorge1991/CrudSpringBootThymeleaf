package com.springbootapplication.SpringBootApplication.Controllers;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbootapplication.SpringBootApplication.Entity.Department;
import com.springbootapplication.SpringBootApplication.Repository.DepartmentRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/department")
public class DepartmentController  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	DepartmentRepository departmentRepository;

	@GetMapping(value = "/departments")
	public String departments(Model model) {
		model.addAttribute("departments", departmentRepository.findAll());
		return "Department/departments";
	}
	
	
	/* ADD */
	
	@GetMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("department", new Department());
		return "Department/adddepartment";
	}
	
	@PostMapping(value = "/adddepartment")
	public String adddepartment(@Valid Department department,BindingResult bindingResult,RedirectAttributes redirectAttributes) {
	
		//validar si tiene algun error, con bindingResult
		
		if (bindingResult.hasErrors()) {
			return "Department/adddepartment";
		}
		
		try {
			departmentRepository.save(new Department(department.getName()));
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Información");
			redirectAttributes.addFlashAttribute("msgbody", "Departamento agregado correctamente");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al agregar departamento");
		}
		
		
		return "redirect:/department/departments";
	}
	
	/* END ADD */
	
	
	
	
	
	
	@GetMapping(value = "/deletedepartment/{id}")
	public String deletedepartment(@PathVariable("id") int id,RedirectAttributes redirectAttributes) {
	
		//validar si tiene algun error, con bindingResult
		
		try {
			departmentRepository.delete(new Department(id));
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Información");
			redirectAttributes.addFlashAttribute("msgbody", "Departamento eliminado correctamente");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al eliminar departamento, el departamento que intenta eliminar puede estar asociado a un empleado");
		}
		
		
		return "redirect:/department/departments";
	}
	
	
	
	/* UPDATE */
	
	@GetMapping(value = "/update/{id}")
	public String update(@PathVariable(value = "id") int id,Model model) {
		System.err.println(id);
		model.addAttribute("department", departmentRepository.findById(id).get());
		return "Department/updatedepartment";
	}
	
	
	
	@PostMapping(value = "/updatedepartment")
	public String updatedepartment(@Valid @ModelAttribute(value = "department") Department department,  BindingResult bindingResult,RedirectAttributes redirectAttributes) {
	
		//validar si tiene algun error, con bindingResult
		
		if (bindingResult.hasErrors()) {
			return "Department/adddepartment";
		}
		
		try {
			System.err.println("dfdfdf"  +department.getId());
			departmentRepository.save(new Department(department.getId(), department.getName()));
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Información");
			redirectAttributes.addFlashAttribute("msgbody", "Departamento modificado correctamente");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al modificar departamento");
		}
		
		
		return "redirect:/department/departments";
	}
	
	/* END UPDATE */
	

}
