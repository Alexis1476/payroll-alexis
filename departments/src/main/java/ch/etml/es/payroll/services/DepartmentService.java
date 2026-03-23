package ch.etml.es.payroll.services;

import ch.etml.es.payroll.config.EmployeeServiceProperties;
import ch.etml.es.payroll.controllers.DepartmentAlreadyExistsException;
import ch.etml.es.payroll.entities.Department;
import ch.etml.es.payroll.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private static DepartmentRepository repository = null;
    private static EmployeeServiceProperties employeeServiceProperties = null;

    public DepartmentService(DepartmentRepository repository, EmployeeServiceProperties employeeServiceProperties) {
        DepartmentService.repository = repository;
        DepartmentService.employeeServiceProperties = employeeServiceProperties;
    }

    public static Department create(Department department) {
        Department existing = repository.findByAcronym(department.getAcronym())
                .orElse(null);

        if (existing != null) {
            throw new DepartmentAlreadyExistsException(department.getAcronym());
        }
        return repository.save(department);
    }

    public Department hireEmployee(Long departmentId, Long employeeId) {
        return null;
    }
}
