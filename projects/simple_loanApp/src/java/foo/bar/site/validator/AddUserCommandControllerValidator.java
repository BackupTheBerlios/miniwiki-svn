package foo.bar.site.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import foo.bar.site.controller.AddUserCommandControllerCommand;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class AddUserCommandControllerValidator implements Validator {
    public boolean supports(Class clazz) {
        return AddUserCommandControllerCommand.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        AddUserCommandControllerCommand command = (AddUserCommandControllerCommand) target;
        if (command.getUsername() == null || (command.getUsername().trim().length()<=0)) {
            errors.rejectValue("username", "manageAddUser.form.validation.username", "username needed");
        }
        if (command.getPassword() == null || (command.getPassword().trim().length()<=0)) {
            errors.rejectValue("password", "manageAddUser.form.validation.password", "password needed");
        }
        if (command.getConfirmPassword() == null || (command.getConfirmPassword().trim().length()<=0)) {
            errors.rejectValue("confirmPassword", "manageAddUser.form.validation.confirmPassword", "Confirm password needed");
        }
        if (command.getPassword() != null && command.getConfirmPassword() != null && (!command.getConfirmPassword().equals(command.getPassword()))) {
            errors.reject("manageAddUser.form.validation.passwordMismatch", "Password mismatch");
        }
    }
}
