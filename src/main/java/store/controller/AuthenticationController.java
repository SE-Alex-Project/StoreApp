package store.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import store.models.User;
import store.repos.UserRepo;
import store.security.JWT.JwtTokenUtil;
import store.services.Implementation.UserService;
import store.validation.AdvanceInfo;
import store.validation.ValidPassword;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Map;

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
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserRepo userRepo;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody Map<String,String> request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.get("email"), request.get("password")));
        final User user = userRepo.getById(request.get("email"));
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
        userRepo.save(userService.handleUser(user));
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
