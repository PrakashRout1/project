package com.nt.service;

import java.util.List;

import com.nt.dto.EmployeeDTO;
import com.nt.entity.Employee;


public interface IEmployeeService {
	public List<EmployeeDTO> fetchEmployees();
	public String registerEmployee(Employee emp) ;
}
