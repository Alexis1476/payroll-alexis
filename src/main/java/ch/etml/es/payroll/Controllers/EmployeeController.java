package ch.etml.es.payroll.Controllers;

import ch.etml.es.payroll.Repositories.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("v1/employees")
public class EmployeeController {

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ch.etml.es.payroll.Entities.Employee create(@RequestBody ch.etml.es.payroll.Entities.Employee newEmployee) {
        if(repository.existsByName(newEmployee.getName()))
            throw new EmployeeAlreadyExistsException(newEmployee.getName());

        return repository.save(newEmployee);
    }

    /* curl sample :
    curl -X GET localhost:8080/api/v1/employees | jq
    */
    @GetMapping
    List<ch.etml.es.payroll.Entities.Employee> all() {
        return repository.findAll();
    }

    /* curl sample :
    curl -X GET localhost:8080/api/v1/employees/1
    */
    @GetMapping("/{id}")
    ch.etml.es.payroll.Entities.Employee one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }
}
