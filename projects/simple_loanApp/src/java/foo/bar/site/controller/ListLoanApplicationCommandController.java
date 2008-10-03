package foo.bar.site.controller;

import foo.bar.site.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;

/**
 * @author tmjee
 * @version 
 */
public class ListLoanApplicationCommandController extends DefaultCommandController {

    private Service service;

    public ListLoanApplicationCommandController(Service service) {
        this.service = service;    
    }

    protected void onFailure(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
        _handle(request, response, command, bindException);
    }

    protected void onSuccess(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
        _handle(request, response, command, bindException);
    }

    private void _handle(HttpServletRequest request, HttpServletResponse response, Object _command, BindException bindException) {
        ListLoanApplicationCommandControllerCommand command = (ListLoanApplicationCommandControllerCommand) _command;
        command.setLoanApplications(service.getAllLoanApplications());
    }
}
