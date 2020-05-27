package CSCI5308.GroupFormationTool.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import CSCI5308.GroupFormationTool.Model.User;


public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final User user = (User) obj;  
        return user.getPassword().equals(user.getConfirmPassword());
    }

}

