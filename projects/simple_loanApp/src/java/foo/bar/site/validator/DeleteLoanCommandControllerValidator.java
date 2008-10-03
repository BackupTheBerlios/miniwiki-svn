package foo.bar.site.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import foo.bar.site.controller.DeleteLoanCommandControllerCommand;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class DeleteLoanCommandControllerValidator implements Validator {

    public boolean supports(Class clazz) {
        return DeleteLoanCommandControllerCommand.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        DeleteLoanCommandControllerCommand command = (DeleteLoanCommandControllerCommand) target;
        if (command.getLoanIds().size()<=0) {
            errors.rejectValue("loanIds", "manageLoanApplication.table.failure.noLoanSelected", "Mo loan selected");
        }
    }
}
