package store.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import store.models.*;

@CrossOrigin
@RequestMapping("/")
@RestController
public class test {

    @PostMapping("test")
    private addEmployeeRequest Hello(@RequestBody addEmployeeRequest e) {
        System.out.println(e);
//        Employee e = new Employee();
//        e.setUser(new User("Ahmed","Ahmed","Ahmed","Ahmed","Ahmed",new Cart(), Role.EMPLOYEE));
//        e.setStore(new Store());
        return e;
    }

    @Data
    public static class addEmployeeRequest{
        private User user;
        private Integer store;
        private Double Salary;
    }
}
