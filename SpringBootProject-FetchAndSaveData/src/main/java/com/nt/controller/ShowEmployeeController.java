package com.nt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.dto.EmployeeDTO;
import com.nt.entity.Employee;
import com.nt.service.IEmployeeService;

@Controller
public class ShowEmployeeController {

	@Autowired
	private IEmployeeService employeeService;
	
	@GetMapping("/welcome")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/list_emps")
	public String showEmp(Map<String, Object> map) {
		//using service
		List<EmployeeDTO> listdto=employeeService.fetchEmployees();
		//keeping result in Model Attribute
		map.put("listDTO", listdto);
		return "show_emps";
	}
	
	@GetMapping("/saveEmp")  //for initial request
	public String  showEmpRegistrationPage(@ModelAttribute("empFrm") Employee emp) {
		return "employee_register";
	}
	
	@PostMapping("/saveEmp")
	public String saveEmployee(RedirectAttributes redirect, 
            @ModelAttribute("empFrm") Employee emp,
            BindingResult errors) {
		String result=employeeService.registerEmployee(emp);
		redirect.addFlashAttribute("resultmsg",result);
		return "redirect:list_emps";
	}
}
