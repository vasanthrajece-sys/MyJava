package com.example.rediscachedemo.employee;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    private final Map<Long, Employee> store = new ConcurrentHashMap<>();

    public EmployeeService() {
        store.put(1L, new Employee(1L, "Vasanth", "Backend", 85000));
        store.put(2L, new Employee(2L, "Karthik", "DevOps", 78000));
        store.put(3L, new Employee(3L, "Priya", "QA", 68000));
    }

    public Collection<Employee> findAll() {
        return store.values();
    }

    @Cacheable(value = "employees", key = "#id")
    public Employee findById(Long id) {
        log.info("Redis does not have employee {} now. Reading from local store.", id);
        sleep();
        return store.get(id);
    }

    @CachePut(value = "employees", key = "#id")
    public Employee update(Long id, String name, String department, double salary) {
        Employee employee = new Employee(id, name, department, salary);
        store.put(id, employee);
        log.info("Employee {} updated. Cache value also updated.", id);
        return employee;
    }

    @CacheEvict(value = "employees", key = "#id")
    public void delete(Long id) {
        store.remove(id);
        log.info("Employee {} removed from local store and Redis cache.", id);
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Thread interrupted", e);
        }
    }
}
