package store.controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/")
@RestController
public class test {

    @PostMapping("test")
    private String Hello() {
        System.out.println("Hello");
        return "Hello Test";
    }
}
