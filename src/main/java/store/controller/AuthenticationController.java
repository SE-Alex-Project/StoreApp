package store.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import store.validation.AdvanceInfo;
import store.validation.ValidPassword;
import store.models.Cart;
import store.models.Role;
import store.models.User;
import store.repos.CartRepo;
import store.repos.UserRepo;
import store.security.JWT.JwtTokenUtil;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

//    @ControllerAdvice
//    public static class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
//
//        @ExceptionHandler(MethodArgumentNotValidException.class)
//        protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
////            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
//            return handleExceptionInternal(ex, ex.getMessage(),
//                    new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//        }
//    }

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
        final User user = userRepo.getById(request.getEmail());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok("{\"token\": \"" + token + "\"}");
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
    public void signUp(@Validated({AdvanceInfo.class}) @RequestBody User user) {
        if (userRepo.existsById(user.getEmail()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Email is already in use!");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.CUSTOMER);
        Cart cart = new Cart();
        user.setCart(cart);
        cart.setUser(user);
        userRepo.save(user);
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
