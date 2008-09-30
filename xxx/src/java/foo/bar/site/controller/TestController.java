package foo.bar.site.controller;

import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class TestController extends DefaultCommandController {


    protected void onSuccess(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
        bindException.reject("testing 1234");    
    }

    protected void onFailure(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
        bindException.reject("testing 123");
    }
}
