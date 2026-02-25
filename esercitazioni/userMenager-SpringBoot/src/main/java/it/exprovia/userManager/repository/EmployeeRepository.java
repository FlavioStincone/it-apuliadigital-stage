package it.exprovia.userManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.exprovia.userManager.model.entity.Employee;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByName(String name);

    List<Employee> findByPwd(String pwd);
    
}
