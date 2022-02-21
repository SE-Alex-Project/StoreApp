package store.services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import store.models.Employee;
import store.models.restModles.AddEmployee;
import store.repos.EmployeeRepo;
import store.repos.StoreRepo;
import store.repos.UserRepo;
import store.services.interfaces.EmployeeServiceInterface;

import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    @Autowired
    StoreRepo storeRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    UserService userService;

    @Override
    public void addEmployee(AddEmployee addEmployee) {
        if (userRepo.existsById(addEmployee.getUser().getEmail()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Email is already in use!");
        Employee e = new Employee();
        e.setUser(userService.handleUser(addEmployee.getUser()));
        e.setStore(storeRepo.getById(addEmployee.getStore()));
        e.setSalary(addEmployee.getSalary());
        employeeRepo.save(e);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployee(String employeeEmail) {
        return employeeRepo.getById(employeeEmail);
    }
}
