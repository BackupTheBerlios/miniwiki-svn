package foo.bar.site.controller;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foo.bar.site.service.Service;

import java.util.Map;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class ManageUserFormController extends DefaultFormController {


    private Service service;

    public ManageUserFormController(Service service) {
        this.service = service;
    }

    protected void onFailure(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
        super.onFailure(request, response, command, bindException);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected void onSuccess(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
        super.onSuccess(request, response, command, bindException);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected void initBinder(HttpServletRequest httpServletRequest, ServletRequestDataBinder servletRequestDataBinder) throws Exception {
        
    }

    protected Map referenceData(HttpServletRequest httpServletRequest, Object command, Errors errors) throws Exception {

        

        return null;
    }
}
