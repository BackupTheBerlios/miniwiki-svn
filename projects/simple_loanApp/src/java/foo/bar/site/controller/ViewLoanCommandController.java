package foo.bar.site.controller;

import foo.bar.site.service.Service;
import foo.bar.site.domain.LoanApplication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class ViewLoanCommandController extends DefaultCommandController {

    private Service service;

    public ViewLoanCommandController(Service service) {
        this.service = service;
    }

    protected void onSuccess(HttpServletRequest request, HttpServletResponse response, Object _command, BindException bindException) {
        ViewLoanCommandControllerCommand command = (ViewLoanCommandControllerCommand) _command;
        LoanApplication loan = service.getLoanApplication(command.getLoanApplicationId());
        command.setLoanApplication(loan);
    }
}
