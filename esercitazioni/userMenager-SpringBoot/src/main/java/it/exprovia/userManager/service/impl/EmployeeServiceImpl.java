package it.exprovia.userManager.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.exprovia.userManager.mapper.EmployeeMapper;
import it.exprovia.userManager.model.dto.EmployeeDTO;
import it.exprovia.userManager.model.entity.Employee;
import it.exprovia.userManager.model.entity.RoleEnum;
import it.exprovia.userManager.repository.EmployeeRepository;
import it.exprovia.userManager.service.IEmployee;



@Service
public class  EmployeeServiceImpl implements IEmployee {
    
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeMapper mapper;

    //Uso Dei Logger
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public List<EmployeeDTO> getEmployees() {
        // Restituisce tutte gli utenti presenti
        List<EmployeeDTO> employeesDTO = mapper.toListDTO(repository.findAll());
        
        return employeesDTO;
        //return repository.findAll().stream().map(mapper::toDTO).toList(); //(senza utilizzare il metodo toListDTO nel mapper)
    }

    @Override
    public EmployeeDTO getEmployee(Long id) {
        // Restituisce un utente specifica in base all'id
        logger.info("Fetching user with id: {"+ id +"}");
        return repository.findById(id).map(mapper::toDTO).orElse(null);
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        // Crea l'utente
        logger.info("Creating new user: {"+ employeeDTO.name() +"}");
        Employee employee = mapper.toEntity(employeeDTO);
        employee.setRole(RoleEnum.EMPLOYEE.getValue());
        repository.save(employee);

        return mapper.toDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> existingEmployee = repository.findById(id);

        if (existingEmployee.isPresent()) {
            Employee employeeToUpdate = existingEmployee.get();

            employeeToUpdate.setName(employeeDTO.name());
            employeeToUpdate.setEmail(employeeDTO.email());

            Employee updatedEmployee = repository.save(employeeToUpdate);

            return mapper.toDTO(updatedEmployee);
        }

        // Meglio lanciare un'eccezione o restituire Optional
        return null;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        // Elimina l'utente 
        if (repository.existsById(id)) {
            logger.info("Deleting user with id: {"+ id +"}");
            repository.deleteById(id);
            return true;
        }
        return false; 
    }

     @Override
    public boolean login(String name, String pwd){

        List<Employee> employeesByName = repository.findByName(name);

        if (!employeesByName.isEmpty()) {

            Employee employee = employeesByName.get(0);
            if (employee.getPwd().equals(pwd)){
                return true;
            }
        }

        return false;

    }



}
