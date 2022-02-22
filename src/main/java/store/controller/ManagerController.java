package store.controller;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import store.models.Employee;
import store.models.User;
import store.models.restModles.EmployeeRequest;
import store.security.JWT.JwtTokenUtil;
import store.services.interfaces.CustomerService;
import store.services.interfaces.EmployeeService;
import store.services.interfaces.ProductService;
import store.validation.AdvanceInfo;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/addEmployee")
    public void addEmployee(@Validated(AdvanceInfo.class) @RequestBody EmployeeRequest employeeRequest) {
        employeeService.addEmployee(employeeRequest);
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
    public void modifyEmployee(@RequestBody EmployeeRequest employee) {
        employeeService.modifyEmployee(employee);
    }

    @GetMapping("/topUsers")
    public List<User> topUsers(@RequestParam("count") Integer count) {
        return customerService.topUsers(count);
    }


    @GetMapping("/totalSales")
    public List<Map<String,String>> totalSales() {
        return productService.totalSales();
    }

    @GetMapping("/top10Sales")
    public List<Map<String,String>> topSales(@RequestParam("count") Integer count) {
        return productService.topSales(count);
    }

}
