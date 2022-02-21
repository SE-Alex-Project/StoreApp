package store.services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import store.models.Employee;
import store.models.restModles.EmployeeRequest;
import store.repos.EmployeeRepo;
import store.repos.StoreRepo;
import store.repos.UserRepo;

import java.util.List;

@Service
public class EmployeeServiceImpl implements store.services.interfaces.EmployeeService {

    @Autowired
    StoreRepo storeRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    UserServiceImpl userService;

    @Override
    public void addEmployee(EmployeeRequest employeeRequest) {
        if (userRepo.existsById(employeeRequest.getUser().getEmail()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Email is already in use!");
        Employee e = new Employee();
        e.setUser(userService.handleUser(employeeRequest.getUser()));
        e.setStore(storeRepo.getById(employeeRequest.getStore()));
        e.setSalary(employeeRequest.getSalary());
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
