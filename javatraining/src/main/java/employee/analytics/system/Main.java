package employee.analytics.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {
	
	public static void main(String[] args) {
		
		List<Employee> employeeList = new ArrayList<>();
		
	    employeeList.add(new Employee("Alice", "IT", 55000));
	    employeeList.add(new Employee("Bob", "Finance", 60000));
	    employeeList.add(new Employee("Alice", "HR", 52000)); // duplicate name
	    employeeList.add(new Employee("Ken", "IT", 60000));
	    employeeList.add(new Employee("Maria", "HR", 50000));
	    employeeList.add(new Employee("John", "Finance", 70000));
	    employeeList.add(new Employee("Ken", "Finance", 65000)); // duplicate name
	    employeeList.add(new Employee("Lara", "IT", 62000));
	    employeeList.add(new Employee("Sam", "HR", 48000));
	    employeeList.add(new Employee("Bob", "IT", 59000)); // duplicate name
		
	    // A. Remove duplicate employee names
        Set<String> duplicateEmployees = new HashSet<>();
        List<Employee> cleanedEmployees = new ArrayList<>();

        for (Employee employee : employeeList) {
            if (duplicateEmployees.add(employee.getName())) {
                cleanedEmployees.add(employee);
            }
        }

        for (Employee employee : cleanedEmployees) {
            System.out.println(employee.getName());
        }
        
        System.out.println();
        
        // B. Group employees by department
        Map<String, List<Employee>> employeeMap = new HashMap<>();
        
        employeeMap = employeeList.stream().collect(
        		Collectors.groupingBy(emp -> emp.getDepartment()));
        
        employeeMap.forEach((dept, empList) -> {
        	System.out.println(dept + ":");
        	empList.forEach(emp -> 
        		System.out.println(" - " + emp.getName() + " (" + emp.getSalary() + ")"));
        	System.out.println();
        });
        
        // C. Find the highest-paid employee in each department
        for (var entry : employeeMap.entrySet()) {
            double max = 0;
            String name = "";

            for (Employee emp : entry.getValue()) {
                if (emp.getSalary() > max) {
                    max = emp.getSalary();
                    name = emp.getName();
                }
            }
            
            System.out.println(entry.getKey() + " employee with the highest salary: " + name + " with salary of " + max);
        }
        
        System.out.println();
        
        // D. Sort all employees by salary (descending)
        employeeList.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        
        System.out.println("Employees sorted by salary:");
        for (Employee employee : employeeList) {
            System.out.println(employee.getName() + " - " + employee.getSalary());
        }
        
        System.out.println();
        
        // E. Create a Set of all unique salaries
        Set<Double> uniqueSalaries = new TreeSet<>();
        
        for (Employee employee : cleanedEmployees) {
        	uniqueSalaries.add(employee.getSalary());
        }
        
        System.out.println("Unique salaries in sorted order:");
        for (Double salary : uniqueSalaries) {
            System.out.println(salary);
        }

	}
}
