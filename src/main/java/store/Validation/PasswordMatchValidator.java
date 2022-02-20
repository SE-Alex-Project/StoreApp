package store.Validation;

import store.controller.AuthenticationController;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, AuthenticationController.RegistrationRequest> {

    @Override
    public void initialize(PasswordMatch p) {

    }

    public boolean isValid(AuthenticationController.RegistrationRequest request, ConstraintValidatorContext c) {
        String plainPassword = request.getPassword();
        String repeatPassword = request.getConfirmPassword();

        if (plainPassword == null || !plainPassword.equals(repeatPassword)) {
            return false;
        }

        return true;
    }
}
