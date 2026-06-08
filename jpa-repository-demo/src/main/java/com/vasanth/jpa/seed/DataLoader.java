package com.vasanth.jpa.seed;

import com.vasanth.jpa.department.Department;
import com.vasanth.jpa.department.DepartmentRepository;
import com.vasanth.jpa.employee.Employee;
import com.vasanth.jpa.employee.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DataLoader(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) {
        Department backend = departmentRepository.save(new Department("Backend"));
        Department devops = departmentRepository.save(new Department("DevOps"));
        Department qa = departmentRepository.save(new Department("QA"));

        employeeRepository.save(new Employee("Vasanth", 30, 90000, true, backend));
        employeeRepository.save(new Employee("Karthik", 29, 76000, true, devops));
        employeeRepository.save(new Employee("Priya", 27, 68000, true, qa));
        employeeRepository.save(new Employee("Meena", 32, 82000, true, backend));
        employeeRepository.save(new Employee("Ravi", 26, 62000, false, qa));
    }
}
