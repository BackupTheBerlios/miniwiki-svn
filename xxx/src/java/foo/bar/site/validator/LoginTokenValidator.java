package foo.bar.site.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import foo.bar.site.domain.LoginToken;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class LoginTokenValidator implements Validator {
    public boolean supports(Class aClass) {
        return LoginToken.class.isAssignableFrom(aClass);
    }

    public void validate(Object target, Errors errors) {
        LoginToken loginToken = (LoginToken) target;
        if ((loginToken.getUsername() == null) || (loginToken.getUsername().trim().length() <= 0)) {
            errors.rejectValue("username", "login.validation.username.required", "Username MANDATORY!!!");
        }
        if ((loginToken.getPassword() == null) || (loginToken.getPassword().trim().length() <= 0)) {
            errors.rejectValue("password", "login.validation.password.required", "Password MANDATORY!!!");
        }
    }
}
