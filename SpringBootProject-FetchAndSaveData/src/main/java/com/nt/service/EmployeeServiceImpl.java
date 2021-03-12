package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dto.EmployeeDTO;
import com.nt.entity.Employee;
import com.nt.repository.IEmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeRepository empRepository; 
	
	@Override
	public List<EmployeeDTO> fetchEmployees() {
		List<Employee> employees=empRepository.findAll();
		
		List<EmployeeDTO> listDTO=new ArrayList<>();
		for(Employee e:employees) {
			EmployeeDTO dto=new EmployeeDTO();
			dto.setId(e.getId());
			dto.setDept(e.getDept());
			dto.setName(e.getName());
			dto.setSalary(e.getSalary());
			listDTO.add(dto);
		}
		return listDTO;
	}

	@Override
	public String registerEmployee(Employee emp) {
		emp=empRepository.save(emp);
		
		return emp!=null?"Employee Registered with"+emp.getId():"Employee not registered";
	}

}
