package store.services.interfaces;

import store.models.Employee;
import store.models.restModles.EmployeeRequest;

import java.util.List;

public interface EmployeeService {
    void addEmployee(EmployeeRequest employeeRequest);
    List<Employee> getAllEmployees();

    Employee getEmployee(String employeeEmail);

    void modifyEmployee(EmployeeRequest employee);
}
