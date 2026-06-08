import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Interview {
    public static void main(String[] args) {
        List<Employee> employees = createEmployees();

        System.out.println("All employees:");
        employees.forEach(System.out::println);

        System.out.println("\nEmployees with salary greater than 60000:");
        employees.stream()
                .filter(employee -> employee.getSalary() > 60000)
                .forEach(System.out::println);

        System.out.println("\nEmployees sorted by salary high to low:");
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .forEach(System.out::println);

        System.out.println("\nHighest paid employee:");
        Optional<Employee> highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
        highestPaid.ifPresent(System.out::println);

        System.out.println("\nEmployees grouped by department:");
        Map<String, List<Employee>> employeesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        employeesByDepartment.forEach((department, employeeList) -> {
            System.out.println(department);
            employeeList.forEach(System.out::println);
        });

        System.out.println("\nAverage salary:");
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);
        System.out.println(averageSalary);

        Map<String,Optional<Employee>> maxByDepartment = employees.stream().collect(Collectors.groupingBy(e->e.getDepartment(),Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
        maxByDepartment.forEach((k,v)-> System.out.println(k +"-"+ v));

        //first non-repetive char
        String s = "Vasanth";
        Map<Character,Long> map = s.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()));
        map.entrySet().stream().filter(e->e.getValue()==1).findFirst().ifPresent(System.out::println);
        //max
        map.entrySet().stream().max(Comparator.comparingDouble(Map.Entry::getValue)).ifPresent(System.out::println);



    }
    //isPalindrome
    public boolean isPalindrome(int num){
        return String.valueOf(num).equals(new StringBuilder(num+"").reverse().toString());
    }
    private static List<Employee> createEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Arun", "IT", 28, 65000));
        employees.add(new Employee(2, "Meena", "HR", 32, 54000));
        employees.add(new Employee(3, "Ravi", "IT", 26, 72000));
        employees.add(new Employee(4, "Priya", "Finance", 30, 80000));
        employees.add(new Employee(5, "Kiran", "HR", 25, 48000));
        return employees;
    }
}
