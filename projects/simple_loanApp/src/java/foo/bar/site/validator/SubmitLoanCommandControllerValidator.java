package foo.bar.site.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import foo.bar.site.controller.SubmitLoanCommandControllerCommand;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class SubmitLoanCommandControllerValidator implements Validator {
    public boolean supports(Class clazz) {
        return SubmitLoanCommandControllerCommand.class.isAssignableFrom(clazz);
    }

    
    public void validate(Object target, Errors errors) {
        SubmitLoanCommandControllerCommand command = (SubmitLoanCommandControllerCommand) target;
        if (command.getSurName() == null || (command.getSurName().trim().length() <=0)) {
            errors.rejectValue("surName", "applyLoanApplication.validation.surName", "Sur name missing");
        }
        if (command.getFirstName() == null || (command.getFirstName().trim().length()<=0)) {
            errors.rejectValue("firstName", "applyLoanApplication.validation.firstName", "First name missing");
        }
        if (command.getAddress() == null || (command.getAddress().trim().length() <=0)) {
            errors.rejectValue("address", "applyLoanApplication.validation.address", "Address missing");
        }
        if (command.getIcNumber() == null || (command.getIcNumber().trim().length() <=0)) {
            errors.rejectValue("icNumber", "applyLoanApplication.validation.icNumber", "Ic Number missing");
        }
        if (command.getHandphoneNumber() == null || (command.getHandphoneNumber().trim().length() <=0)) {
            errors.rejectValue("handphoneNumber", "applyLoanApplication.validation.handhoneNumber", "Hand phone number missing");
        }
    }
}
