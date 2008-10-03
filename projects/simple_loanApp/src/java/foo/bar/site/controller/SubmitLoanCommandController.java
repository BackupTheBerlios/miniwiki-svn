package foo.bar.site.controller;

import foo.bar.site.service.Service;
import foo.bar.site.domain.LoanApplication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;

import java.util.Date;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class SubmitLoanCommandController extends DefaultCommandController {

    private Service service;

    public SubmitLoanCommandController(Service service) {
        this.service = service;
    }

    protected void onSuccess(HttpServletRequest request, HttpServletResponse response, Object _command, BindException bindException) {
        SubmitLoanCommandControllerCommand command =(SubmitLoanCommandControllerCommand)_command;
        LoanApplication loan = new LoanApplication();
        loan.setSurName(command.getSurName());
        loan.setFirstName(command.getFirstName());
        loan.setAge(command.getAge());
        loan.setAddress(command.getAddress());
        loan.setEmail(command.getEmail());
        loan.setTelephoneNumber(command.getTelephoneNumber());
        loan.setHandphoneNumber(command.getHandphoneNumber());
        loan.setAnualIncome(command.getAnualIncome());
        loan.setReferralHpNumber(command.getReferralHpNumber());
        loan.setApplicationDate(new Date());
        service.addLoanApplication(loan);
        command.reset();
        bindException.reject("applyLoanApplication.form.success", "Ok submited");
    }

    protected void onFailure(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
    }
}
