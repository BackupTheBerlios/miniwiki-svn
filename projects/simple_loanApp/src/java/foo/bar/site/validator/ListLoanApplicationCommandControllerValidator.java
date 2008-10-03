package foo.bar.site.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import foo.bar.site.controller.ListLoanApplicationCommandControllerCommand;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class ListLoanApplicationCommandControllerValidator implements Validator {
    public boolean supports(Class clazz) {
        return ListLoanApplicationCommandControllerCommand.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        
    }
}
