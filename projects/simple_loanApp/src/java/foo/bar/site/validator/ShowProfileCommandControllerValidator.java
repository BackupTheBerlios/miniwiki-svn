package foo.bar.site.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import foo.bar.site.controller.ShowProfileCommandControllerCommand;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class ShowProfileCommandControllerValidator implements Validator {
    public boolean supports(Class clazz) {
        return ShowProfileCommandControllerCommand.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        
    }
}
