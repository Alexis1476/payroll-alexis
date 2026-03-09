<<<<<<< HEAD:src/main/java/ch/etml/es/payroll/Controllers/EmployeeAlreadyExistsException.java
package ch.etml.es.payroll.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmployeeAlreadyExistsException extends RuntimeException {
    public EmployeeAlreadyExistsException(String name) {
        super("Employee " + name + " already exists");
    }
=======
package ch.etml.es.payroll.controllers;

public class EmployeeAlreadyExistsException extends RuntimeException{

    public EmployeeAlreadyExistsException(String name){
        super("Employee " + name + " already exists");}
>>>>>>> feature/putEmployee:employees/src/main/java/ch/etml/es/payroll/controllers/EmployeeAlreadyExistsException.java
}
