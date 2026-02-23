package ch.etml.es.payroll.services;

import ch.etml.es.payroll.Controllers.EmployeeAlreadyExistsException;
import ch.etml.es.payroll.Entities.Employee;
import ch.etml.es.payroll.Repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private static EmployeeRepository employeeRepository;

    EmployeeService(EmployeeRepository employeeRepository) {
        EmployeeService.employeeRepository = employeeRepository;
    }

    public static Employee hire(Employee employee) {
        if (employeeRepository.existsByName(employee.getName()))
            throw new EmployeeAlreadyExistsException(employee.getName());

        return employeeRepository.save(employee);
    }
}
