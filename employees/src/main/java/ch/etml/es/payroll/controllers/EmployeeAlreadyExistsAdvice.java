<<<<<<< HEAD:src/main/java/ch/etml/es/payroll/Controllers/EmployeeAlreadyExistsAdvice.java
package ch.etml.es.payroll.Controllers;
=======
package ch.etml.es.payroll.controllers;
>>>>>>> feature/putEmployee:employees/src/main/java/ch/etml/es/payroll/controllers/EmployeeAlreadyExistsAdvice.java

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmployeeAlreadyExistsAdvice {
<<<<<<< HEAD:src/main/java/ch/etml/es/payroll/Controllers/EmployeeAlreadyExistsAdvice.java
    @ResponseBody
    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String employeeAlreadyExistsHandler(EmployeeAlreadyExistsException ex){
=======

    @ResponseBody
    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String employeeNotFoundHandler(EmployeeAlreadyExistsException ex){
>>>>>>> feature/putEmployee:employees/src/main/java/ch/etml/es/payroll/controllers/EmployeeAlreadyExistsAdvice.java
        return ex.getMessage();
    }
}
