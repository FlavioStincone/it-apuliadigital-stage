package it.exprovia.userManager.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import it.exprovia.userManager.model.dto.EmployeeDTO;
import it.exprovia.userManager.model.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDTO toDTO(Employee employees);
    
    Employee toEntity(EmployeeDTO employeeDTO);
    
    List<EmployeeDTO> toListDTO(List<Employee> employees);
    
    List<Employee> toListEntity(List<EmployeeDTO> employeesDTO);
}
