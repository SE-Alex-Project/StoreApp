package store.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import store.Validation.AdvanceInfo;
import store.Validation.PasswordMatch;
import store.Validation.ValidPassword;
//import store.models.Role;
import store.models.Cart;
import store.models.Customer;
import store.models.Role;
import store.models.User;
import store.repos.CartRepo;
import store.repos.CustomerRepo;
import store.repos.UserRepo;
import store.security.JWT.JwtTokenUtil;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserRepo userRepo;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        final User user = userRepo.getByEmail(request.getEmail());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok("{\"token\": \""+ token +"\"}");
    }


    /*
    {
    "email":"ahmed@gmail.com",
    "fname":"Ahmed",
    "lname":"Abdallah",
    "password":"@Test1Ahmed",
    "confirmPassword":"@Test1Ahmed"
    }
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Validated(AdvanceInfo.class) @RequestBody User user) {
        if (userRepo.existsByEmail(user.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.CUSTOMER);
        Customer customer = new Customer();
        customer.setUser(user);
        userRepo.save(user);
        Cart cart = new Cart();
        cart.setCustomer(customer);
        customerRepo.save(customer);
        cartRepo.save(cart);
        customer.setCart(cart);
        customerRepo.save(customer);
        return ResponseEntity.ok().body(user);
    }


    @Data
    public static class LoginRequest {
        @NotBlank(message = "User Email is required")
        @Email
        private String email;
        @NotBlank(message = "User Password is required")
        @ValidPassword
        private String password;
    }


//    @Data
//    @PasswordMatch(message = "{register.repeatPassword.mismatch}")
//    public static class RegistrationRequest {
//
//        @NotBlank(message = "User Email is required")
//        @Email
//        private String email;
//        @NotBlank(message = "User first Name is required")
//        private String fName;
//        @NotBlank(message = "User Last Name is required")
//        private String lName;
//
//        @NotBlank(message = "User Password is required")
//        @ValidPassword
//        private String password;
//
//        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//        private String confirmPassword;
//
//        public User getCustomer() {
//            User c = new User();
//            c.setEmail(email);
//            c.setFName(fName);
//            c.setLName(lName);
//            c.setPassword(password);
//            return c;
//        }
//    }

}
