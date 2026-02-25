package it.exprovia.userManager.service;

import java.util.List;

import it.exprovia.userManager.model.dto.EmployeeDTO;

public interface IEmployee {

    List<EmployeeDTO> getEmployees();

    EmployeeDTO getEmployee(Long id);

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

    boolean deleteEmployee(Long id);

    boolean login(String name, String pwd);
    
}
