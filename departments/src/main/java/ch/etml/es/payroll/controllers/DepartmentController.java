package ch.etml.es.payroll.controllers;

import ch.etml.es.payroll.entities.Department;
import ch.etml.es.payroll.repositories.DepartmentRepository;
import ch.etml.es.payroll.services.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/departments")
public class DepartmentController {

    private final DepartmentRepository repository;
    private final DepartmentService departmentService;

    DepartmentController(DepartmentRepository repository, DepartmentService departmentService) {
        this.repository = repository;
        this.departmentService = departmentService;
    }

    /* curl sample :
    curl -X GET localhost:8080/api/v1/departements | jq
    */
    @GetMapping("")
    List<Department> all() {
        return repository.findAll();
    }

    /* curl sample :
    curl -X GET localhost:8080/api/v1/departments/1
    */
    @GetMapping("/{id}")
    Department one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    /* curl sample :
        curl -i -X POST localhost:8080/api/v1/departments ^
            -H "Content-type:application/json" ^
            -d "{\"acronym\": \"MKT\", \"description\": \"Marketing\"}"
    */
    @PostMapping("")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department created = DepartmentService.create(department);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(created);
    }

    @PostMapping("/{departmentId}/employees")
    public ResponseEntity<Department> hireEmployee(@PathVariable Long departmentId, @RequestBody Map<String, Long> body) {
        // Récupérer les informations du body
        Long employeeId = body.get("employeeId");

        // Appeler le service du departments afin de réaliser l'engagement
        Department updated = departmentService.hireEmployee(departmentId, employeeId);

        return ResponseEntity.ok(updated);
    }
}
