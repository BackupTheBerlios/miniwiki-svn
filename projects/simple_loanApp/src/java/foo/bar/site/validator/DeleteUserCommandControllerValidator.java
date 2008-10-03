package foo.bar.site.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import foo.bar.site.controller.DeleteUserCommandControllerCommand;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class DeleteUserCommandControllerValidator implements Validator {

    public boolean supports(Class clazz) {
        return DeleteUserCommandControllerCommand.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        DeleteUserCommandControllerCommand command = (DeleteUserCommandControllerCommand) target;

        if (command.getUserIds().size() <= 0) {
            errors.rejectValue("userIds", "deleteUser.validation.userIds.required", "Need to check the checkboxes");
        }
    }
}
