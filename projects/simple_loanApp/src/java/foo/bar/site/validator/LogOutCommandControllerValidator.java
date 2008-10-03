package foo.bar.site.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import foo.bar.site.controller.LogOutCommandControllerCommand;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class LogOutCommandControllerValidator implements Validator {
    public boolean supports(Class aClass) {
        return LogOutCommandControllerCommand.class.isAssignableFrom(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
