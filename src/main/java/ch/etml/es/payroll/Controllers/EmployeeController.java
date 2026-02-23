package ch.etml.es.payroll.Controllers;

import ch.etml.es.payroll.Entities.Employee;
import ch.etml.es.payroll.Repositories.EmployeeRepository;
import ch.etml.es.payroll.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/employees")
public class EmployeeController {
    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    ResponseEntity<Employee> create(@RequestBody Employee newEmployee) {
        Employee savedEmployee = EmployeeService.hire(newEmployee);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedEmployee.getId()).toUri();

        return ResponseEntity.created(location).body(savedEmployee);
    }

    /* curl sample :
    curl -X GET localhost:8080/api/v1/employees | jq
    */
    @GetMapping
    List<Employee> all() {
        return repository.findAll();
    }

    /* curl sample :
    curl -X GET localhost:8080/api/v1/employees/1
    */
    @GetMapping("/{id}")
    Employee one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }
}
