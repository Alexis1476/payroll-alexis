package ch.etml.es.payroll.Controllers;

import ch.etml.es.payroll.Repositories.EmployeeRepository;
import ch.etml.es.payroll.Entities.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository){
        this.repository = repository;
    }

    /* curl sample :
    curl -X GET localhost:8080/api/v1/employees | jq
    */
    @GetMapping("/")
    List<Employee> all(){
        return repository.findAll();
    }

    /* curl sample :
    curl -X GET localhost:8080/api/v1/employees/1
    */
    @GetMapping("/{id}")
    Employee one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    /* curl sample :
        curl -i -X POST localhost:8080/api/v1/employees ^
            -H "Content-type:application/json" ^
            -d "{\"name\": \"Russel George\", \"role\": \"gardener\"}"
    */
    @PostMapping("")
    public ResponseEntity<Employee> hireEmployee(@RequestBody Employee employee) {
        Employee created = EmployeeServicePost.hire(employee);

        return ResponseEntity
                .created(URI.create("/api/v1/employees/" + created.getId()))
                .body(created);
    }
}
