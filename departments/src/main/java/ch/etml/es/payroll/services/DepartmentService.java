package ch.etml.es.payroll.services;

import ch.etml.es.payroll.config.EmployeeServiceProperties;
import ch.etml.es.payroll.controllers.DepartmentAlreadyExistsException;
import ch.etml.es.payroll.controllers.DepartmentNotFoundException;
import ch.etml.es.payroll.controllers.EmployeeAlreadyHiredException;
import ch.etml.es.payroll.entities.Department;
import ch.etml.es.payroll.repositories.DepartmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DepartmentService {

    private static DepartmentRepository repository = null;
    private String employeeServiceUrl = null;

    public DepartmentService(DepartmentRepository repository, EmployeeServiceProperties employeeServiceProperties) {
        DepartmentService.repository = repository;
        this.employeeServiceUrl = employeeServiceProperties.getUrl();
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
        Department department = repository.findById(departmentId).orElse(null);
        if (department == null) {
            throw new DepartmentNotFoundException(departmentId);
        }

        if (department.hasEmployee(employeeId)) {
            throw new EmployeeAlreadyHiredException(employeeId);
        }

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.getForEntity(this.employeeServiceUrl + "/" + employeeId, Void.class);

        // If employee doesn't exist
        if (response.getStatusCode().is4xxClientError()) {
            // TODO Return ? 404
            return null;
        }
        department.addEmployee(employeeId);
        repository.save(department);
        return department;
    }
}