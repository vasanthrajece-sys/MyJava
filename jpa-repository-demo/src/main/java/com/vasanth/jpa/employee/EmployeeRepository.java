package com.vasanth.jpa.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    List<Employee> findByDepartmentName(String departmentName);

    List<Employee> findBySalaryGreaterThan(double salary);

    List<Employee> findByNameContainingIgnoreCase(String name);

    Optional<Employee> findFirstByOrderBySalaryDesc();

    boolean existsByName(String name);

    long countByDepartmentName(String departmentName);

    void deleteByName(String name);

    Page<Employee> findByActiveTrue(Pageable pageable);

    List<EmployeeNameSalaryProjection> findByActiveTrue();

    @Query("select e from Employee e where e.department.name = :departmentName and e.salary >= :salary")
    List<Employee> findUsingJpql(@Param("departmentName") String departmentName, @Param("salary") double salary);

    @Query(value = "select * from employee e where e.salary = (select max(salary) from employee)", nativeQuery = true)
    List<Employee> findHighestSalaryUsingNativeQuery();

    @Query("select new com.vasanth.jpa.employee.EmployeeSummary(e.name, e.department.name, e.salary) from Employee e")
    List<EmployeeSummary> findEmployeeSummary();

    @Modifying
    @Query("update Employee e set e.salary = :salary where e.id = :id")
    int updateSalary(@Param("id") Long id, @Param("salary") double salary);

    static Specification<Employee> hasDepartment(String departmentName) {
        return (root, query, builder) -> departmentName == null
                ? builder.conjunction()
                : builder.equal(root.get("department").get("name"), departmentName);
    }

    static Specification<Employee> salaryGreaterThanOrEqual(Double salary) {
        return (root, query, builder) -> salary == null
                ? builder.conjunction()
                : builder.greaterThanOrEqualTo(root.get("salary"), salary);
    }
}
