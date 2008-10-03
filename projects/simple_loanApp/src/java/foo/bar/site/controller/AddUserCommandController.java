package foo.bar.site.controller;

import foo.bar.site.service.Service;
import foo.bar.site.service.DuplicateUserException;
import foo.bar.site.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class AddUserCommandController extends DefaultCommandController {

    private Service service;

    public AddUserCommandController(Service service) {
        this.service = service;
    }

    protected void onSuccess(HttpServletRequest request, HttpServletResponse response, Object _command, BindException bindException) {
        AddUserCommandControllerCommand command = (AddUserCommandControllerCommand) _command;
        User user = new User();
        user.setUsername(command.getUsername().trim());
        user.setPassword(command.getPassword().trim());
        try {
            service.addUser(user);
            command.clear();
            bindException.reject("manageAddUser.form.success", "Ok");
        }
        catch(DuplicateUserException e){
            bindException.reject("manageAddUser.form.duplicateUser", "Duplicate user");
        }
    }

    protected void onFailure(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
    }
}
