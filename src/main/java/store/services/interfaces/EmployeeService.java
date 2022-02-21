package store.services.interfaces;

import store.models.Employee;
import store.models.restModles.AddEmployee;

import java.util.List;

public interface EmployeeService {
    void addEmployee(AddEmployee addEmployee);
    List<Employee> getAllEmployees();

    Employee getEmployee(String employeeEmail);
}
