package foo.bar.site.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import foo.bar.site.controller.UpdateProfileCommandControllerCommand;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UpdateProfileCommandControllerValidator implements Validator {
    public boolean supports(Class clazz) {
        return UpdateProfileCommandControllerCommand.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        UpdateProfileCommandControllerCommand command = (UpdateProfileCommandControllerCommand) target;

        if (command.getPassword() == null || (command.getPassword().trim().length()<=0)) {
            errors.rejectValue("password", "manageProfile.form.validation.password", "Password required");
        }
        if (command.getConfirmPassword() == null || (command.getPassword().trim().length()<=0)) {
            errors.rejectValue("confirmPassword", "manageProfile.form.validation.confirmPassword", "confirm password req");
        }
        if (command.getConfirmPassword() != null && command.getPassword() != null && (!command.getConfirmPassword().equals(command.getPassword()))) {
            errors.reject("manageProfile.form.validation.passwordMismatch", "password mismatch");
        }
    }
}
