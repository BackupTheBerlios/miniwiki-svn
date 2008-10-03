package foo.bar.site.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import foo.bar.site.domain.User;
import foo.bar.site.controller.LoginFormControllerCommand;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class LoginFormControllerCommandValidator implements Validator {
    public boolean supports(Class aClass) {
        return LoginFormControllerCommand.class.isAssignableFrom(aClass);
    }

    public void validate(Object target, Errors errors) {
        LoginFormControllerCommand command = (LoginFormControllerCommand) target;
        if ((command.getUsername() == null) || (command.getUsername().trim().length() <= 0)) {
            errors.rejectValue("username", "login.validation.username.required", "Username MANDATORY!!!");
        }
        if ((command.getPassword() == null) || (command.getPassword().trim().length() <= 0)) {
            errors.rejectValue("password", "login.validation.password.required", "Password MANDATORY!!!");
        }
    }
}
