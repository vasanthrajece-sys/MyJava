package com.vasanth.jpa.employee;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public List<Employee> findByDepartment(String department) {
        return employeeRepository.findByDepartmentName(department);
    }

    public List<Employee> salaryGreaterThan(double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }

    public List<Employee> sortedBySalaryDesc() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "salary"));
    }

    public Page<Employee> activeEmployeesPage(int page, int size) {
        return employeeRepository.findByActiveTrue(PageRequest.of(page, size, Sort.by("name")));
    }

    public List<EmployeeNameSalaryProjection> activeEmployeeProjection() {
        return employeeRepository.findByActiveTrue();
    }

    public List<EmployeeSummary> employeeSummary() {
        return employeeRepository.findEmployeeSummary();
    }

    public List<Employee> search(String department, Double minSalary) {
        Specification<Employee> specification = Specification
                .where(EmployeeRepository.hasDepartment(department))
                .and(EmployeeRepository.salaryGreaterThanOrEqual(minSalary));

        return employeeRepository.findAll(specification);
    }

    @Transactional
    public int updateSalary(Long id, double salary) {
        return employeeRepository.updateSalary(id, salary);
    }

    @Transactional
    public void deleteByName(String name) {
        employeeRepository.deleteByName(name);
    }
}
