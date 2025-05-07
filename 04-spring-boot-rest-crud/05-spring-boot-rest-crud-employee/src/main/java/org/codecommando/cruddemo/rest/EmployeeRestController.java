package org.codecommando.cruddemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.codecommando.cruddemo.entity.Employee;
import org.codecommando.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    private ObjectMapper objectMapper;

    // quickly and dirty: inject employee dao
    public EmployeeRestController(EmployeeService theEmployeeService, ObjectMapper theObjectMapper) {
        employeeService = theEmployeeService;
        objectMapper = theObjectMapper;
    }

    // expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // expose "/employees/{employeeId}" and return employee
    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if (employee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee save(@RequestBody Employee theEmployee) {
        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theEmployee.setId(0);
        return employeeService.save(theEmployee);
    }

    @PutMapping("/employees")
    public Employee update(@RequestBody Employee theEmployee) {
        return employeeService.save(theEmployee);
    }

    private Employee applyPatch(Map<String, Object> patchPayloads, Employee employee) {
        // Apply the patch to the employee object

        // Convert the employee object to a JSON node
        ObjectNode employeeNode = objectMapper.convertValue(employee, ObjectNode.class);

        // Convert the patch payloads to a JSON node
        ObjectNode patchNode = objectMapper.convertValue(patchPayloads, ObjectNode.class);

        // Apply the patch to the employee node
        employeeNode.setAll(patchNode);

        // This is a placeholder for actual patching logic
        return objectMapper.convertValue(employeeNode, Employee.class);

    }

    @PatchMapping("/employees/{employeeId}")
    public Employee partialUpdate(@PathVariable int employeeId, @RequestBody Map<String, Object> theEmployee) {

        Employee findEmployee = employeeService.findById(employeeId);
        if (findEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        if (theEmployee.containsKey("id")) {
            throw new RuntimeException("Employee id is not allow in request body - " + employeeId);
        }

        Employee patchEmployee = applyPatch(theEmployee, findEmployee);

        return employeeService.save(patchEmployee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public Employee delete(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);
        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);
        return tempEmployee;
    }
}
