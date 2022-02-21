package store.models.restModles;

import lombok.Data;
import store.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "User Email is required")
    @Email
    private String email;
    @NotBlank(message = "User Password is required")
    @ValidPassword
    private String password;
}