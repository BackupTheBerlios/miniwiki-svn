package foo.bar.site.controller;

import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foo.bar.site.service.Service;
import foo.bar.site.service.AuthenticationException;
import foo.bar.site.interceptor.AccessInterceptor;
import foo.bar.site.domain.User;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class LoginFormController extends DefaultFormController {

    private Service service;

    public LoginFormController(Service service) {
        this.service = service;
    }

    protected void onSubmitSuccess(HttpServletRequest request, HttpServletResponse response, Object _command, BindException bindException) {
        LoginFormControllerCommand command = (LoginFormControllerCommand) _command;
        try {
            User user = service.authenticate(command);
            request.getSession(true).setAttribute(AccessInterceptor.USER_SESSION_ID, user);
        }
        catch(AuthenticationException e) {
            bindException.reject("login.validation.badUsernamePassword", "Bad Login !!!!");   
        }
    }
}
