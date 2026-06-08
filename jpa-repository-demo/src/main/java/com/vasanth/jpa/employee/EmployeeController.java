package com.vasanth.jpa.employee;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jpa/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> allEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/department/{department}")
    public List<Employee> byDepartment(@PathVariable String department) {
        return employeeService.findByDepartment(department);
    }

    @GetMapping("/salary-greater-than")
    public List<Employee> salaryGreaterThan(@RequestParam double salary) {
        return employeeService.salaryGreaterThan(salary);
    }

    @GetMapping("/sorted")
    public List<Employee> sortedBySalary() {
        return employeeService.sortedBySalaryDesc();
    }

    @GetMapping("/page")
    public Page<Employee> page(@RequestParam int page, @RequestParam int size) {
        return employeeService.activeEmployeesPage(page, size);
    }

    @GetMapping("/projection")
    public List<EmployeeNameSalaryProjection> projection() {
        return employeeService.activeEmployeeProjection();
    }

    @GetMapping("/dto")
    public List<EmployeeSummary> dtoProjection() {
        return employeeService.employeeSummary();
    }

    @GetMapping("/search")
    public List<Employee> search(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) Double minSalary) {
        return employeeService.search(department, minSalary);
    }

    @PutMapping("/{id}/salary")
    public String updateSalary(@PathVariable Long id, @RequestParam double salary) {
        int updated = employeeService.updateSalary(id, salary);
        return "Rows updated: " + updated;
    }

    @DeleteMapping("/name/{name}")
    public String deleteByName(@PathVariable String name) {
        employeeService.deleteByName(name);
        return "Deleted employee with name: " + name;
    }
}
