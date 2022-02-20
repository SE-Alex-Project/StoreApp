package store.Validation;

import store.models.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, User> {

    @Override
    public void initialize(PasswordMatch p) {

    }

    public boolean isValid(User user, ConstraintValidatorContext c) {
        String plainPassword = user.getPassword();
        String repeatPassword = user.getConfirmPassword();

        if (plainPassword == null || !plainPassword.equals(repeatPassword)) {
            return false;
        }

        return true;
    }
}
