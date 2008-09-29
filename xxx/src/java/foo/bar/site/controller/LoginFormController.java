package foo.bar.site.controller;

import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foo.bar.site.service.Service;
import foo.bar.site.service.AuthenticationException;
import foo.bar.site.domain.LoginToken;
import foo.bar.site.interceptor.AccessInterceptor;

import java.io.IOException;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class LoginFormController extends DefaultFormController {

    private Service service;

    public LoginFormController(Service service) {
        this.service = service;
    }

    protected void onSuccess(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
        LoginToken loginToken = (LoginToken) command;
        try {
            loginToken = service.authenticate(loginToken);
            request.getSession(true).setAttribute(AccessInterceptor.ACCESS_SESSION_ID, loginToken);
        }
        catch(AuthenticationException e) {
            bindException.reject("login.validation.badUsernamePassword", "Bad Login !!!!");   
        }
    }
}
