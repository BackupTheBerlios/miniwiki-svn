package foo.bar.site.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import foo.bar.site.controller.ViewLoanCommandControllerCommand;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class ViewLoanCommandControllerValidator implements Validator {

    public boolean supports(Class clazz) {
        return ViewLoanCommandControllerCommand.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        ViewLoanCommandControllerCommand command = (ViewLoanCommandControllerCommand) target;
        if (command.getLoanApplicationId() < 0) {
            errors.rejectValue("loanApplicationId", "manageLoanApplication.viewLoan.failure.noLoanApplicationId", "No Loan Application Id");
        }
    }
}
