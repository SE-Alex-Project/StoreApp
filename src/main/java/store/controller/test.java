package store.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.models.*;
import store.models.restModles.AddEmployee;
import store.repos.UserRepo;
import store.services.Implementation.EmployeeService;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RequestMapping("/")
@RestController
public class test {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserRepo userRepo;

    @GetMapping("test")
    private Object Hello() {
        return userRepo.topUser(10);
    }
}
