package com.vasanth.jpa.employee;

public class EmployeeSummary {
    private final String name;
    private final String departmentName;
    private final double salary;

    public EmployeeSummary(String name, String departmentName, double salary) {
        this.name = name;
        this.departmentName = departmentName;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public double getSalary() {
        return salary;
    }
}
