package store.controller;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ManagerController {

    /*{
        "token" : "token",
        "email":"user email",
        "firstName": "user first name",
        "lastName": "user last name",
        "password": "user hashed password",
        "store":"1",
        "erole":role,
        "salary":salary
    }*/
    @PostMapping("/addEmployee")
    public void addEmployee(@RequestBody JSONObject employee) {

    }

    @PostMapping("/getEmployees")
    public void getEmployees(@RequestBody String token) {

    }

    /*
     * {
     * "token": token
     * "email":emp-email
     * }
     */

    @PostMapping("/getEmployeeInfo")
    public void getEmployeeInfo(@RequestBody JSONObject getEmp) {

    }

    @PostMapping("/getManagerInfo")
    public void getManagerInfo(@RequestBody String token) {

    }


    @PostMapping("/modifyEmployee")
    public void modifyEmployee(@RequestBody JSONObject employee) {

    }

    @GetMapping("/top10Customers")
    public void topCustomers() {

    }


    @GetMapping("/totalSales")
    public void totalSales() {

    }

    @GetMapping("/top10Sales")
    public void topSales() {

    }

}
