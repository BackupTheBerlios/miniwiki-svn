package foo.bar.site.controller;

import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foo.bar.site.service.Service;
import foo.bar.site.interceptor.AccessInterceptor;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class LogOutCommandController extends DefaultCommandController {

    private Service service;

    public LogOutCommandController(Service service) {
        this.service = service;
    }


    protected void onSuccess(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
        _handle(request, response, command, bindException);
    }

    protected void onFailure(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
        _handle(request, response, command, bindException);
    }

    private void _handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
        request.getSession(true).removeAttribute(AccessInterceptor.USER_SESSION_ID);
    }
}
