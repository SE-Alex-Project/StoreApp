package store.controller;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import store.models.Employee;
import store.models.User;
import store.models.restModles.AddEmployee;
import store.security.JWT.JwtTokenUtil;
import store.services.Implementation.CustomerService;
import store.services.Implementation.EmployeeService;
import store.validation.AdvanceInfo;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    CustomerService customerService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/addEmployee")
    public void addEmployee(@Validated(AdvanceInfo.class) @RequestBody AddEmployee addEmployee) {
        employeeService.addEmployee(addEmployee);
    }

    @PostMapping("/getEmployees")
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }


    @PostMapping("/getEmployeeInfo")
    public Employee getEmployeeInfo(@RequestParam(name = "EId") String employeeEmail) {
        return employeeService.getEmployee(employeeEmail);
    }

    @PostMapping("/getInfo")
    public Employee getManagerInfo(@RequestHeader("Authorization") String token) {
        String userEmail = jwtTokenUtil.getUserEmailFromToken(jwtTokenUtil.parseHeaderAuth(token));
        return employeeService.getEmployee(userEmail);
    }


    @PostMapping("/modifyEmployee")
    public void modifyEmployee(@RequestBody JSONObject employee) {

    }

    @GetMapping("/topUsers")
    public List<User> topUsers(@RequestParam("count") Integer count) {
        return customerService.topUsers(count);
    }


    @GetMapping("/totalSales")
    public void totalSales() {

    }

    @GetMapping("/top10Sales")
    public void topSales() {

    }

}
