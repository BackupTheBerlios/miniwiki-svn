package foo.bar.site.controller;

import foo.bar.site.service.Service;
import foo.bar.site.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class ShowProfileCommandController extends DefaultCommandController implements UserAware {

    private Service service;
    private User user;

    public ShowProfileCommandController(Service service) {
        this.service = service;
    }

    protected void onFailure(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
    }

    protected void onSuccess(HttpServletRequest request, HttpServletResponse response, Object _command, BindException bindException) {
        ShowProfileCommandControllerCommand command = (ShowProfileCommandControllerCommand) _command;

        if (user != null) {
            command.setId(user.getId());
            command.setUsername(user.getUsername());
            command.setPassword(user.getPassword());
            command.setConfirmPassword(user.getPassword());
        }
        else {
            bindException.reject("manageProfile.form.notLogIn", "Not logged in");
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}
