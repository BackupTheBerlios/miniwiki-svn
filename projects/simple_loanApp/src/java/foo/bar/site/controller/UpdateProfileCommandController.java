package foo.bar.site.controller;

import foo.bar.site.service.Service;
import foo.bar.site.domain.User;
import foo.bar.site.interceptor.AccessInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UpdateProfileCommandController extends DefaultCommandController {

    private Service service;

    public UpdateProfileCommandController(Service service) {
        this.service = service;    
    }

    protected void onFailure(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
        
    }

    protected void onSuccess(HttpServletRequest request, HttpServletResponse response, Object _command, BindException bindException) {
        UpdateProfileCommandControllerCommand command = (UpdateProfileCommandControllerCommand)_command;
        User user = new User();
        user.setId(command.getId());
        user.setUsername(command.getUsername());
        user.setPassword(command.getPassword());
        service.updateUser(user);
        User _updatedUser = service.getUser(command.getId());
        if (_updatedUser != null) {
            request.getSession(true).setAttribute(AccessInterceptor.USER_SESSION_ID, _updatedUser);
            bindException.reject("manageProfile.form.success", "Success");
        }
        else {
            bindException.reject("manageProfile.form.noSuchUser", "No Such User");

        }
    }
}
